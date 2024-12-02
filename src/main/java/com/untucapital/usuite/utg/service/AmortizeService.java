package com.untucapital.usuite.utg.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.untucapital.usuite.utg.dto.amortize.AmortizationTable;
import com.untucapital.usuite.utg.dto.amortize.LoanObject;
import com.untucapital.usuite.utg.dto.amortize.PeriodObj;
import com.untucapital.usuite.utg.dto.amortize.ResultObject;
import com.untucapital.usuite.utg.dto.loans.SingleLoan;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.processor.AmortizeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AmortizeService {

    private final MusoniService musoniService;
    private final RestClient restClient;
    private final AmortizeProcessor amortizeProcessor;

    public AmortizeService(MusoniService musoniService, RestClient restClient, AmortizeProcessor amortizeProcessor) {
        this.musoniService = musoniService;
        this.restClient = restClient;
        this.amortizeProcessor = amortizeProcessor;
    }

    @Transactional(value = "transactionManager")
    public String getTable(String loanId, String periodRange) {

        log.info("LOAN ID IS: " + loanId);

        SingleLoan loan = restClient.getLoanId(loanId);

        String accountNo = loan.getAccountNo();
        int clientId = loan.getClientId();
        String clientName = loan.getClientName();
        String loanProductName = loan.getLoanProductName();
        String loanOfficerName = loan.getLoanOfficerName();
        double principal = Double.parseDouble(String.valueOf(loan.getPrincipal()));
        int numberOfRepayments = loan.getNumberOfRepayments();
        double interestRatePerPeriod = loan.getInterestRatePerPeriod();


        int[] actualDisbursementDateArray = loan.getTimeline().getActualDisbursementDate();
        int actualDisbursementYear = actualDisbursementDateArray[0];
        int actualDisbursementMonth = actualDisbursementDateArray[1];
        int actualDisbursementDay = actualDisbursementDateArray[2];
        LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

        int[] expectedMaturityDateArray = loan.getTimeline().getExpectedMaturityDate();
        int expectedMaturityYear = expectedMaturityDateArray[0];
        int expectedMaturityMonth = expectedMaturityDateArray[1];
        int expectedMaturityDay = expectedMaturityDateArray[2];
        LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);

        log.info("Account Number: " + accountNo);
        log.info("Client ID: " + clientId);
        log.info("Client Name: " + clientName);
        log.info("Loan Product Name: " + loanProductName);
        log.info("Loan Officer Name: " + loanOfficerName);
        log.info("Principal: " + principal);
        log.info("Number of Repayments: " + numberOfRepayments);
        log.info("Interest Rate per Period: " + interestRatePerPeriod);
        log.info("Actual Disbursement Date: " + actualDisbursementDate);
        log.info("Expected Maturity Date: " + expectedMaturityDate);

//        COLLECT VALUES FROM LOAN..

        int n = numberOfRepayments; // Number of periods
        double i = interestRatePerPeriod; // Interest per year
        double pv = principal; // Present Value
        double fv = 0; // Future Value

        double pmt = pv * (i / 100) / (1 - Math.pow(1 + (i / 100), -n));
        double remainingBalance = pv;
        double totalInterest = 0;

        JsonArray tableArray = new JsonArray();

        AmortizationTable amortizationTable = new AmortizationTable();


        LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
        int qw = 24;
        double interest = 0.0;
        List<PeriodObj> periodObjList = new ArrayList<PeriodObj>();
        for (int period = 1; period <= n; period++) {
            interest = remainingBalance * (i / 100);
            principal = pmt - interest;
            remainingBalance -= principal;
            totalInterest += interest;

//            JsonObject periodObject = new JsonObject();
            PeriodObj periodObj = new PeriodObj();
            periodObj.setPeriod(period);
            periodObj.setMonth(nextMonthDate.toString());
            periodObj.setMonth_int(qw);
            periodObj.setPV(pv);
            periodObj.setPMT(pmt);
            periodObj.setInterest(interest);
            periodObj.setFV(fv);


            periodObjList.add(periodObj);

            log.info("PeriodObjList:{}", periodObj);

            pv = remainingBalance;
            nextMonthDate = nextMonthDate.plusMonths(1);
            qw++;
            log.info(String.valueOf(nextMonthDate));
        }
        amortizationTable.setAmortizationTable(periodObjList);

        ResultObject resultObject = new ResultObject();
        resultObject.setAmortizationTable(amortizationTable);
        resultObject.setTotalInterest(totalInterest);
        resultObject.setPeriodInterest1(amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);

        log.info(String.valueOf(amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest)));

        return json;
    }


    @Transactional(value = "transactionManager")
    public String getLoanInterest(String rangeStart, String rangeEnd, String periodRange) {
        JsonArray resultArray = new JsonArray();

        for (int i = Integer.parseInt(rangeStart); i <= Integer.parseInt(rangeEnd); i++) {
            LoanObject loanObject = new LoanObject();

            try {
                SingleLoan loan = restClient.getLoanId(String.valueOf(i));

                int[] actualDisbursementDateArray = loan.getTimeline().getActualDisbursementDate();
                int actualDisbursementYear = actualDisbursementDateArray[0];
                int actualDisbursementMonth = actualDisbursementDateArray[1];
                int actualDisbursementDay = actualDisbursementDateArray[2];
                LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

                int[] expectedMaturityDateArray = loan.getTimeline().getExpectedMaturityDate();
                int expectedMaturityYear = expectedMaturityDateArray[0];
                int expectedMaturityMonth = expectedMaturityDateArray[1];
                int expectedMaturityDay = expectedMaturityDateArray[2];
                LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                if (LocalDate.parse(periodRange, formatter).isAfter(actualDisbursementDate.plusMonths(1)) ||
                        LocalDate.parse(periodRange, formatter).isEqual(actualDisbursementDate.plusMonths(1))) {
                    // Extracting the required values
                    // Set the properties of the LoanObject using the POJO properties
                    loanObject.setAccountNo(loan.getAccountNo());
                    loanObject.setStatus(String.valueOf(loan.getStatus().isActive()));
                    loanObject.setClientId(loan.getClientId());
                    loanObject.setClientName(loan.getClientName());
                    loanObject.setLoanProductName(loan.getLoanProductName());
                    loanObject.setLoanOfficerName(loan.getLoanOfficerName());
                    loanObject.setPrincipal(loan.getPrincipal());
                    loanObject.setNumberOfRepayments(loan.getNumberOfRepayments());
                    loanObject.setInterestRatePerPeriod(loan.getInterestRatePerPeriod());
                    loanObject.setActualDisbursementDate(actualDisbursementDate.toString());
                    loanObject.setExpectedMaturityDate(expectedMaturityDate.toString());

                    int n = loan.getNumberOfRepayments(); // Number of periods
                    double in = loan.getInterestRatePerPeriod(); // Interest per year
                    double pv = loan.getPrincipal(); // Present Value
                    double fv = 0; // Future Value
                    double pmt = pv * (in / 100) / (1 - Math.pow(1 + (in / 100), -n));
                    double remainingBalance = pv;
                    double totalInterest = 0;

                    List<PeriodObj> periods = new ArrayList<>();

                    LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
                    double interest = 0.0;
                    double principal = 0.0;
                    double lastNonZeroPeriodInterest = 0.0;

                    double periodInterest2 = 0;
                    for (int period = 1; period <= n; period++) {
                        interest = remainingBalance * (in / 100);
                        principal = pmt - interest;
                        remainingBalance -= principal;
                        totalInterest += interest;

                        PeriodObj periodObject = new PeriodObj();
                        periodObject.setPeriod(period);
                        periodObject.setMonth(nextMonthDate.toString());
                        periodObject.setPV(pv);
                        periodObject.setPMT(pmt);
                        periodObject.setInterest(interest);
                        periodObject.setFV(fv);
                        periodInterest2 = amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest);
                        periodObject.setPeriodInterest2(periodInterest2);

                        periods.add(periodObject);
                        pv = remainingBalance;
                        nextMonthDate = nextMonthDate.plusMonths(1);
                        System.out.println(nextMonthDate);
                    }

                    //FIXME check-point
                    AmortizationTable amortizationTable = new AmortizationTable();
                    amortizationTable.setAmortizationTable(periods);

                    loanObject.setAmortizationTable(amortizationTable);
                    loanObject.setTotalInterest(totalInterest);
                    loanObject.setPeriodInterest2(periodInterest2);

                    log.info("Installments: {}",amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

                    resultArray.add(String.valueOf(loanObject));

                }


            } catch (Exception e) {
                log.info("Failed to get the interest: {}", e.getMessage());
                // Handle exception if needed
            }
        }
        log.info("\n##################################################################\n" + resultArray);

        JsonObject resultObject = new JsonObject();
        resultObject.add("InterestsTable", resultArray);

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);

        return json;
    }

    @Transactional(value = "transactionManager")
    public String getLoansDisbursedByDate(String rangeStart, String rangeEnd, String periodRange) {
        JsonArray resultArray = new JsonArray();

        List<Integer> range = musoniService.disbursedLoansByDate(rangeStart, rangeEnd);

        for (int i : range) {
            LoanObject loanObject = new LoanObject();

            SingleLoan loan = restClient.getLoanId(String.valueOf(i));

            int[] actualDisbursementDateArray = loan.getTimeline().getActualDisbursementDate();
            //FIXME What should happen when the actualDisbursementDateArray is null
            if(actualDisbursementDateArray==null){
                continue;
            }
            int actualDisbursementYear = actualDisbursementDateArray[0];
            int actualDisbursementMonth = actualDisbursementDateArray[1];
            int actualDisbursementDay = actualDisbursementDateArray[2];
            LocalDate actualDisbursementDate = LocalDate.of(actualDisbursementYear, actualDisbursementMonth, actualDisbursementDay);

            int[] expectedMaturityDateArray = loan.getTimeline().getExpectedMaturityDate();
            int expectedMaturityYear = expectedMaturityDateArray[0];
            int expectedMaturityMonth = expectedMaturityDateArray[1];
            int expectedMaturityDay = expectedMaturityDateArray[2];
            LocalDate expectedMaturityDate = LocalDate.of(expectedMaturityYear, expectedMaturityMonth, expectedMaturityDay);

            // Extracting the required values
            double periodInter = 0.0;
            if (loan.getStatus().isActive()) {

                // Set the properties of the LoanObject using the POJO properties
                loanObject.setAccountNo(loan.getAccountNo());
                loanObject.setStatus(String.valueOf(loan.getStatus().isActive()));
                loanObject.setClientId(loan.getClientId());
                loanObject.setClientName(loan.getClientName());
                loanObject.setLoanProductName(loan.getLoanProductName());
                loanObject.setLoanOfficerName(loan.getLoanOfficerName());
                loanObject.setPrincipal(loan.getPrincipal());
                loanObject.setNumberOfRepayments(loan.getNumberOfRepayments());
                loanObject.setInterestRatePerPeriod(loan.getInterestRatePerPeriod());
                loanObject.setActualDisbursementDate(actualDisbursementDate.toString());
                loanObject.setExpectedMaturityDate(expectedMaturityDate.toString());


                int n = loan.getNumberOfRepayments(); // Number of periods
                double in = loan.getInterestRatePerPeriod(); // Interest per year
                double pv = loan.getPrincipal(); // Present Value
                double fv = 0; // Future Value
                double pmt = pv * (in / 100) / (1 - Math.pow(1 + (in / 100), -n));
                double remainingBalance = pv;
                double totalInterest = 0;

                List<PeriodObj> periods = new ArrayList<>();

                LocalDate nextMonthDate = actualDisbursementDate.plusMonths(1);
                double interest = 0.0;
                double principal = 0.0;
                double lastNonZeroPeriodInterest = 0.0;

                for (int period = 1; period <= n; period++) {
                    interest = remainingBalance * (in / 100);
                    principal = pmt - interest;
                    remainingBalance -= principal;
                    totalInterest += interest;

                    PeriodObj periodObject = new PeriodObj();
                    periodObject.setPeriod(period);
                    periodObject.setMonth(nextMonthDate.toString());
                    periodObject.setPV(pv);
                    periodObject.setPMT(pmt);
                    periodObject.setInterest(interest);
                    periodObject.setFV(fv);

                    double periodInterest3 = amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest);
                    periodObject.setPeriodInterest3(periodInterest3);

                    if (periodInterest3 != 0.0) {
                        lastNonZeroPeriodInterest = periodInterest3;
                    }

                    periods.add(periodObject);
                    pv = remainingBalance;
                    nextMonthDate = nextMonthDate.plusMonths(1);
                }

                AmortizationTable amortizationTable= new AmortizationTable();
                amortizationTable.setAmortizationTable(periods);

                loanObject.setAmortizationTable(amortizationTable);
                loanObject.setTotalInterest(totalInterest);
                loanObject.setPeriodInterest4(lastNonZeroPeriodInterest);


                log.info("LastNonZeroPeriodInterest: {}", lastNonZeroPeriodInterest);
                log.info("####### ************** ############### ************* $$$$$$$$$$$$$$$");
                log.info("Installment: {}",amortizeProcessor.installmentCalc(String.valueOf(nextMonthDate), periodRange, principal, interest));

                resultArray.add(String.valueOf(loanObject));
            }

        }

        log.info("\n##################################################################\n" + range);
//FIXME Replace the json object with pojos
        JsonObject resultObject = new JsonObject();
        resultObject.add("InterestsTable", resultArray);

        Gson gson = new Gson();
        String json = gson.toJson(resultObject);


        return json;
    }

}

