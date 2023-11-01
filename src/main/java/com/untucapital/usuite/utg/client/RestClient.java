package com.untucapital.usuite.utg.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.DTO.AllLoans;
import com.untucapital.usuite.utg.DTO.client.Client;
import com.untucapital.usuite.utg.DTO.loans.LoanTransaction;
import com.untucapital.usuite.utg.DTO.loans.RepaymentScheduleLoan;
import com.untucapital.usuite.utg.DTO.loans.SingleLoan;
import com.untucapital.usuite.utg.exception.LoanListCannotBeNullExceptionHandler;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.transactions.Loans;
import com.untucapital.usuite.utg.model.transactions.PageItem;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class RestClient {

    @Value("${musoni.url}")
    private String baseUrl;

    @Value("${musoni.username}")
    private String username;

    @Value("${musoni.X_API_KEY}")
    private String apiKey;

    @Value("${musoni.password}")
    private String password;

    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String xFineractPlatformTenantId;

    private final RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public RestClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
    }

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);
        headers.set("X-Fineract-Platform-TenantId", xFineractPlatformTenantId);
        headers.set("x-api-key", apiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

    public Loans getLoans(Long timestamp) {
        log.info("Calling musoni to get loans");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Loans loans = new Loans();

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans?modifiedSinceTimestamp=" + MusoniUtils.generateTimestamp(timestamp), HttpMethod.GET, entity, String.class).getBody();
            log.info("Loans in the past 24 hours: {}", loanString);

            loans = objectMapper.readValue(loanString, Loans.class);

            log.info("Loans object: {}", loans);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }

        return loans;
    }

    public List<Transactions> getTransactions(int loanId) {

        log.info("PageItem Id :{}", loanId);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        PageItem pageItem = new PageItem();

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans/" + loanId + "?associations=transactions", HttpMethod.GET, entity, String.class).getBody();

            log.info("PageItem with id " + loanId + " and transaction information: {}", loanString);

            pageItem = objectMapper.readValue(loanString, PageItem.class);

            log.info("Transaction information for a loan: {}", pageItem);
        } catch (Exception e) {
            log.info("Failed to get Loans: {}", e.getMessage());
        }

        List<Transactions> transactions = pageItem.getTransactions();

        if (transactions == null) {

            return null;
        }

        log.info("Transactions : {}", transactions.toString());

        List<Transactions> cashTransactions = new ArrayList<>();
        for (Transactions tx : transactions) {
            if (tx.getType().isDisbursement() || tx.getType().isRepayment()) {

                cashTransactions.add(tx);
            }
            log.info("Transaction with repayment or disbursement: {}", cashTransactions.toString());
        }
        log.info("Transaction with repayment or disbursement: {}", cashTransactions.toString());

        return cashTransactions;

    }

    public List<Employee> getAllUsers() {
        log.info("Calling musoni to get staff");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        List<Employee> employees = new ArrayList<Employee>();
        try {
            String staffString = restTemplate.exchange(baseUrl + "users", HttpMethod.GET, entity, String.class).getBody();
            log.info("Employees in the system: {}", staffString);

            Employee[] employeeList = objectMapper.readValue(staffString, Employee[].class);

            employees = List.of(employeeList);

            log.info("Loans object: {}", Arrays.toString(employeeList));
        } catch (Exception e) {
            log.info("Failed to get Employees", e.getMessage());
        }

        return employees;
    }

    public RepaymentScheduleLoan getRepaymentSchedule(String loanAccount) {

        RepaymentScheduleLoan repaymentScheduleLoan = new RepaymentScheduleLoan();
        try {
            String repaymentSchedule = restTemplate.exchange("https://api.demo.irl.musoniservices.com/v1/loans/"
                    + loanAccount + "?associations=repaymentSchedule", HttpMethod.GET, setHttpEntity(), String.class).getBody();

            repaymentScheduleLoan = objectMapper.readValue(repaymentSchedule, RepaymentScheduleLoan.class);

            log.info("RepaymentScheduleLoan:{}", repaymentScheduleLoan);

        } catch (Exception e) {
            log.info("Failed to get repayment schedule loans:{}", e.getMessage());
        }

        return repaymentScheduleLoan;
    }

    public Client getClientById(String clientId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Client client = new Client();

        try {
            String clientString = restTemplate.exchange(baseUrl + "clients/" + clientId, HttpMethod.GET, entity, String.class).getBody();
            log.info("Loans in the past 24 hours: {}", clientString);

            client = objectMapper.readValue(clientString, Client.class);

            log.info("Client object: {}", client);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }

        return client;
    }

    public String getLoanByTimeStamp(@PathVariable Long timeStamp) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(baseUrl + "loans?modifiedSinceTimestamp=" + timeStamp, HttpMethod.GET, entity, String.class).getBody();
    }

    public Loans getLoansByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Loans loans = new Loans();
        try {
            String loanString = restTemplate.exchange(baseUrl + "loans?disbursementFromDate=" + fromDate + "&disbursementToDate=" + toDate, HttpMethod.GET, entity, String.class).getBody();

            loans = objectMapper.readValue(loanString, Loans.class);

            log.info("Loans object: {}", loans);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }
        return loans;
    }

    public SingleLoan getLoanId(String loanId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        SingleLoan loan = new SingleLoan();

        log.info("Called URL => " + baseUrl + "loans/" + loanId);

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans/" + loanId, HttpMethod.GET, entity, String.class).getBody();
            log.info("loanDetails: " + loanString);

            loan = objectMapper.readValue(loanString, SingleLoan.class);

            log.info("Loan retrieved from Musoni: " + loan);

        } catch (Exception e) {
            log.info("Failed to retrieve loan by id");
        }

        return loan;
    }

    public AllLoans retrieveAllLoans(String fromDate, String toDate) {
        AllLoans allLoans = restTemplate.exchange(baseUrl + "loans/?disbursementFromDate=" + fromDate + "&disbursementToDate=" + toDate + "&limit=100",
                HttpMethod.GET,
                setHttpEntity(),
                new ParameterizedTypeReference<AllLoans>() {
                }).getBody();

        if (allLoans != null) {
            return allLoans;
        }

        throw new LoanListCannotBeNullExceptionHandler("Loan list cannot be null");
    }

    public AllLoans getAllLoans(String branchName, String fromDate, String toDate) {
        AllLoans allLoans = restTemplate.exchange(baseUrl + "loans/?clientOfficeName=" + branchName +
                        "&disbursementFromDate=" + fromDate +
                        "&disbursementToDate=" + toDate + "&limit=100",
                HttpMethod.GET,
                setHttpEntity(),
                new ParameterizedTypeReference<AllLoans>() {
                }).getBody();

        return allLoans;
    }

    public Loans getTimestampedLoanAcc() {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Loans loans = new Loans();

        String timestampedLoanAcc = restTemplate.exchange(baseUrl + "loans?modifiedSinceTimestamp=" + MusoniUtils.getTimestamp(), HttpMethod.GET, entity, String.class).getBody();

        log.info("Timestamped Loan Acc: {}", timestampedLoanAcc);

        try {
            loans = objectMapper.readValue(timestampedLoanAcc, Loans.class);
        } catch (Exception e) {
            log.info("Failed to map TimestampedLoanAccount object:{}", e.getMessage());
        }

        return loans;
    }

    public LoanTransaction getByLoanIdAndTransactionId(String loanId, int transId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        LoanTransaction loanTransaction = new LoanTransaction();

        String loanTransBody = restTemplate.exchange(baseUrl + "loans/" + loanId + "/transactions/" +
                transId, HttpMethod.GET, entity, String.class).getBody();
        log.info("Loan with id " + loanId + "and transactionId" + transId + ": " + loanTransBody);

        try {
            loanTransaction = objectMapper.readValue(loanTransBody, LoanTransaction.class);
        } catch (Exception e) {
            log.info("Failed to read loan transaction", e.getMessage());
        }

        return loanTransaction;
    }

//    public Client getClient(String clientLoans) {
//
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
//
//        String clientAccount = restTemplate.exchange(baseUrl + "clients/" + clientLoans + "/accounts", HttpMethod.GET, entity, String.class).getBody();
//
//        log.info("Client Loans :{}", clientAccount);
//
//        try {
//            loanTransaction = objectMapper.readValue(loanTransBody, LoanTransaction.class);
//        } catch (Exception e) {
//            log.info("Failed to read loan transaction", e.getMessage());
//        }
//
//        return loanTransaction;
//    }

}
