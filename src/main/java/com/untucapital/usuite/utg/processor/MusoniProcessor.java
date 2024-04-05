package com.untucapital.usuite.utg.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.dto.DisbursedLoan;
import com.untucapital.usuite.utg.dto.DisbursedLoanMonth;
import com.untucapital.usuite.utg.dto.DisbursedLoans;
import com.untucapital.usuite.utg.dto.Loan;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.dto.pastel.PastelTransReq;
import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.entity.res.AccountEntityResponseDTO;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import com.untucapital.usuite.utg.exception.VaultNotFoundException;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.repository2.AccountsRepository;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.SmsService;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.service.cms.VaultService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MusoniProcessor {

    private final PostGlRepository postGlRepository;

    private final AccountsRepository accountsRepository;

    private final RestClient restClient;

    private final VaultService vaultService;

    private final AccountService accountService;

    @Value("${pastel.password}")
    private String apiPassword;

    @Value("${pastel.username}")
    private String apiUsername;

    private final SmsService smsService;

    public MusoniProcessor(PostGlRepository postGlRepository, AccountsRepository accountsRepository, RestClient restClient, VaultService vaultService, AccountService accountService, SmsService smsService) {
        this.postGlRepository = postGlRepository;
        this.accountsRepository = accountsRepository;
        this.restClient = restClient;
        this.vaultService = vaultService;
        this.accountService = accountService;
        this.smsService = smsService;
    }

//    public MusoniProcessor(RestClient restClient, VaultService vaultService, AccountService accountService) {
//        this.restClient = restClient;
//        this.vaultService = vaultService;
//        this.accountService = accountService;
//    }
//
//    public List<PostGLRequestDTO> setPostGlFields(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {
//        log.info("Transactions: {}", transactions);
//
//
//        List<PostGLRequestDTO> postGlRequestDTOs = new ArrayList<>();
//        java.util.Date utilDate = new java.util.Date();
//        Date sqlDate = new Date(utilDate.getTime());
//
//        for (Transactions transaction : transactions) {
//            int[] dateArray = transaction.getSubmittedOnDate();
//            log.info("Date Array: {}", Arrays.toString(dateArray));
//
//            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);
//
//            if (isTransactionRequired) {
//                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);
//                Date date = Date.valueOf(formattedDate);
//                log.info("Formatted date: {}", formattedDate);
//
//                String typeValue = transaction.getType().getValue();
//                String submittedUsername = transaction.getSubmittedByUsername();
//                AccountEntityResponseDTO entity = getAccountLink(submittedUsername);
//                if(entity==null){
//                    continue;
//                }
//                float creditAmount = 0.0f;
//                float debitAmount = 0.0f;
//                String reference = "";
//
//                if ("disbursement".equalsIgnoreCase(typeValue)) {
//                    reference = "DIS-" + transaction.getId();
//                    creditAmount = (float) transaction.getAmount();
//                } else if ("repayment".equalsIgnoreCase(typeValue)) {
//                    reference = "REP-" + transaction.getId();
//                    debitAmount = (float) transaction.getAmount();
//                }
//
//                PostGLRequestDTO postGl = new PostGLRequestDTO();
//                postGl.setTxDate(date);
//                postGl.setDTStamp(sqlDate);
//                postGl.setId("JL");
//                postGl.setICurrencyID(1);
//                postGl.setFExchangeRate(1.0f);
//                postGl.setDescription(typeValue);
//                postGl.setBIsJCDocLine(false);
//                postGl.setBIsSTGLDocLine(false);
//                postGl.setBPrintCheque(false);
//                postGl.setIInvLineID(0L);
//                postGl.setBPBTPaid(false);
//                postGl.setBReconciled(false);
//                postGl.setUserName(transaction.getSubmittedByUsername());
//                postGl.setFExchangeRate(0.0f);
//                postGl.setFForeignDebit(0.0f);
//                postGl.setFForeignCredit(0.0f);
//                postGl.setTaxTypeID(0);
//                postGl.setTax_Amount(0.0f);
//                postGl.setProject(0);
//                postGl.setDrCrAccount(0);
//                postGl.setJobCodeLink(0);
//                postGl.setRepID(0);
//                postGl.setFJCRepCost(0.0f);
//                postGl.setIMFPID(0);
//                postGl.setITxBranchID(0);
//                postGl.setIGLTaxAccountID(0);
//                postGl.setPostGL_iBranchID(0);
//                postGl.setIImportDeclarationID(0);
//                postGl.setIMajorIndustryCodeID(0);
//                postGl.setFForeignTax(0.0f);
//
//                postGl.setReference(reference);
//                postGl.setCredit(creditAmount);
//                postGl.setDebit(debitAmount);
//                postGl.setAccountLink(entity.getAccountLink());
//
//                postGlRequestDTOs.add(postGl);
//            }
//        }
//
//        return postGlRequestDTOs;
//    }
//
//
//    public List<PostGLRequestDTO> setPostGlClientLoanBook(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {
//
//
//        List<PostGLRequestDTO> postGlRequestDTOs = new ArrayList<>();
//
//        for (Transactions transaction : transactions) {
//
//            int[] dateArray = transaction.getSubmittedOnDate();
//
//
//            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);
//
//            if (isTransactionRequired) {
//
//                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);
//
//                Date date = Date.valueOf(formattedDate);
//
//                PostGLRequestDTO postGl = new PostGLRequestDTO();
//                PostGlResponseDTO postGlLoanBook = new PostGlResponseDTO();
//
//                postGl.setTxDate(date);
//                // Get the current date and time
//                java.util.Date utilDate = new java.util.Date();
//
//                // Convert the java.util.Date to java.sql.Date
//                Date sqlDate = new Date(utilDate.getTime());
//                postGl.setDTStamp(sqlDate);
//                postGl.setId("JL");
//                postGl.setICurrencyID(1);
//                postGl.setFExchangeRate(1.0f);
//                postGl.setDescription(transaction.getType().getValue());
//                postGl.setBIsJCDocLine(false);
//                postGl.setBIsSTGLDocLine(false);
//                postGl.setBPrintCheque(false);
//                postGl.setIInvLineID(0L);
//                postGl.setBPBTPaid(false);
//                postGl.setBReconciled(false);
//                postGl.setUserName(transaction.getSubmittedByUsername());
//                postGl.setFExchangeRate(0F);
//                postGl.setFForeignDebit(0F);
//                postGl.setFForeignCredit(0F);
//                postGl.setTaxTypeID(0);
//                postGl.setTax_Amount(0F);
//                postGl.setProject(0);
//                postGl.setDrCrAccount(0);
//                postGl.setJobCodeLink(0);
//                postGl.setRepID(0);
//                postGl.setFJCRepCost(0F);
//                postGl.setIMFPID(0);
//                postGl.setITxBranchID(0);
//                postGl.setIGLTaxAccountID(0);
//                postGl.setPostGL_iBranchID(0);
//                postGl.setIImportDeclarationID(0);
//                postGl.setIMajorIndustryCodeID(0);
//                postGl.setFForeignTax(0F);
//
//                String submittedUsername = transaction.getSubmittedByUsername();
//
//                AccountEntityResponseDTO entity = getAccountLink(submittedUsername);
//                if(entity==null){
//                    continue;
//                }
//
//                if (transaction.getType().getValue().equalsIgnoreCase("disbursement")) {
//
//                    postGl.setReference("DIS-" + transaction.getId());
//                    postGl.setCredit(0f);
//                    postGl.setDebit((float) transaction.getAmount());
//                    postGl.setAccountLink(AppConstants.LOAN_BOOK_ACCOUNT_DIS);
//
//                } else if (transaction.getType().getValue().equalsIgnoreCase("repayment")) {
//
//                    postGl.setReference("REP-" + transaction.getId());
//                    postGl.setCredit((float) transaction.getAmount());
//                    postGl.setDebit(0f);
//                    postGl.setAccountLink(AppConstants.LOAN_BOOK_ACCOUNT_REP);
//
//                }
//
//                postGlRequestDTOs.add(postGl);
//            }
//
//        }
//
//        return postGlRequestDTOs;
//    }

    public List<PastelTransReq> setPastelFields(List<Transactions> transactions) throws ParseException, JsonProcessingException, AccountNotFoundException {
        log.info("Transactions: {}", transactions);


        List<PostGLRequestDTO> postGlRequestDTOs = new ArrayList<>();
        List<PastelTransReq>  pastelTransReqList = new ArrayList<>();
        java.util.Date utilDate = new java.util.Date();
        Date sqlDate = new Date(utilDate.getTime());

        for (Transactions transaction : transactions) {
            int[] dateArray = transaction.getSubmittedOnDate();
            log.info("Date Array: {}", Arrays.toString(dateArray));

//            boolean isTransactionRequired = MusoniUtils.isValidDate(dateArray);

//            if (isTransactionRequired) {
                LocalDate formattedDate = MusoniUtils.formatDate(dateArray);
                Date date = Date.valueOf(formattedDate);
                log.info("Formatted date: {}", formattedDate);

                String typeValue = transaction.getType().getValue();
                log.info("TYPE:{}",typeValue);
                String submittedUsername = transaction.getSubmittedByUsername();
                AccountEntityResponseDTO entity = getAccountLink(submittedUsername);

                if(entity==null){
                    continue;
                }
                String reference = "";
                String toAccount = "";
                String fromAccount = "";
                String transactionType = "";

            PastelTransReq pastelTransReq = new PastelTransReq();

                if ("disbursement".equalsIgnoreCase(typeValue)) {

                    fromAccount =getAccount(submittedUsername);
                    if (submittedUsername.equalsIgnoreCase("masimbam")){
                        fromAccount = "8422/000/HRE/FCA";
                    }
                    toAccount= AppConstants.LOAN_BOOK_ACCOUNT_NAME_DIS;
                    transactionType= AppConstants.DISBURSEMENT;
                    reference = "DIS-" + transaction.getId();

//                    smsService.sendSingle("0775797299", "This is a disbursement");


                    pastelTransReq.setAmount(transaction.getAmount());
                    //FIXME set the correct currency
                    pastelTransReq.setCurrency("001");
                    pastelTransReq.setDescription(typeValue);
                    pastelTransReq.setReference(reference);
                    //FIXME put the correct rate
                    pastelTransReq.setExchangeRate(1);
                    pastelTransReq.setFromAccount(fromAccount);
                    pastelTransReq.setToAccount(toAccount);
                    pastelTransReq.setAPIPassword(apiPassword);
                    pastelTransReq.setAPIUsername(apiUsername);
                    pastelTransReq.setTransactionDate(MusoniUtils.formatMusoniDt(dateArray));
                    pastelTransReq.setTransactionType(transactionType);

                }
                if ("repayment".equalsIgnoreCase(typeValue)) {
                    toAccount =getAccount(submittedUsername );
                    if (submittedUsername.equalsIgnoreCase("masimbam")){
                        toAccount = "8422/000/HRE/FCA";
                    }
                    fromAccount= AppConstants.LOAN_BOOK_ACCOUNT_NAME_REP;
                    transactionType = AppConstants.REPAYMENT;
                    reference = "REP-" + transaction.getId();

//                    smsService.sendSingle("0775797299", "This is a repayment");

                    pastelTransReq.setAmount(transaction.getAmount());
                    //FIXME set the correct currency
                    pastelTransReq.setCurrency("001");
                    pastelTransReq.setDescription(typeValue);
                    pastelTransReq.setReference(reference);
                    //FIXME put the correct rate
                    pastelTransReq.setExchangeRate(1);
                    pastelTransReq.setFromAccount(fromAccount);
                    pastelTransReq.setToAccount(toAccount);
                    pastelTransReq.setAPIPassword(apiPassword);
                    pastelTransReq.setAPIUsername(apiUsername);
                    pastelTransReq.setTransactionDate(MusoniUtils.formatMusoniDt(dateArray));
                    pastelTransReq.setTransactionType(transactionType);

                }

                pastelTransReqList.add(pastelTransReq);
            }
//        }

        return pastelTransReqList;
    }

    /**
     * Retrieve all Empoloyees from Musoni and loop through the list to get the office name where a transaction was initiated
     */

    public AccountEntityResponseDTO getAccountLink(String submittedUsername) throws AccountNotFoundException {
        List<Employee> employees = restClient.getAllUsers();

        // Filter out the employee who initiated the transaction
        Optional<Employee> initiator = employees.stream()
                .filter(employee -> employee.getUsername().equals(submittedUsername))
                .findFirst();

        if (initiator.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The user with this username: %s is not in the system", submittedUsername));
        }

        Employee employee = initiator.get();
        String officeName = employee.getOfficeName();

        String subString = " Teller Account";

        if (officeName.contains(subString)) {
            officeName += subString;
        }

        VaultResponseDTO vault = vaultService.getVaultByBranchAndType(officeName, AppConstants.VAULT_TYPE);

        if (vault == null) {
            throw new VaultNotFoundException("Vault not found");
        }

        String accountName = vault.getAccount();
        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(accountName);

        if (accountEntity == null) {
//            throw new AccountNotFoundException("Account not found");
            return null;
        }

        return accountEntity;
    }

    public String getAccount(String submittedUsername) throws AccountNotFoundException {
        List<Employee> employees = restClient.getAllUsers();

        // Filter out the employee who initiated the transaction
        Optional<Employee> initiator = employees.stream()
                .filter(employee -> employee.getUsername().equals(submittedUsername))
                .findFirst();

        if (initiator.isEmpty()) {
            throw new UsernameNotFoundException(String.format("The user with this username: %s is not in the system", submittedUsername));
        }

        Employee employee = initiator.get();
        String officeName = employee.getOfficeName();

        if (employee.getUsername().equalsIgnoreCase("masimbam")){
            officeName = "Harare";
        }
        String subString = " Petty Cash";

        if (officeName.contains(subString)) {
            officeName += subString;
        }


        VaultResponseDTO vault = vaultService.getVaultByBranchAndType(officeName, AppConstants.VAULT_TYPE);

        if (vault == null) {
            throw new VaultNotFoundException("Vault not found");
        }
        String vault1 = vault.getAccount();
        log.info("Vault Acc :{}", vault1);

        return  vault1;
    }


    public List<DisbursedLoan> getDisbursedLoans(List<Loan> loans) {
        List<DisbursedLoan> disbursedLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getTimeline().getActualDisbursementDate() != null) {
                DisbursedLoan disbursedLoan = DisbursedLoan.builder()
                        .loanName(loan.getClientName())
                        .expectedDisbursementDate(loan.getTimeline().getExpectedDisbursementDate())
                        .disbursedAt(loan.getTimeline().getActualDisbursementDate())
                        .loanId(loan.getAccountNo())
                        .disbursedMonth(MusoniUtils.getYearMonth(loan.getTimeline().getActualDisbursementDate()))
                        .principal(loan.getPrincipal())
                        .build();

                disbursedLoans.add(disbursedLoan);
            }
        }
        return disbursedLoans;
    }

    public List<DisbursedLoanMonth> groupByMonth(List<DisbursedLoan> disbursedLoans) {

        List<DisbursedLoanMonth> disbursedLoanMonths = new ArrayList<>();
        //Group the list of returned loans by month and branch.
        Map<String, Map<String, List<DisbursedLoan>>> loansByMonthAndBranch = disbursedLoans.stream()
                .collect(Collectors.groupingBy(DisbursedLoan::getDisbursedMonth,
                        Collectors.groupingBy(DisbursedLoan::getBranch)));


        for (Map.Entry<String, Map<String, List<DisbursedLoan>>> entry : loansByMonthAndBranch.entrySet()) {
            String month = entry.getKey();
            Map<String, List<DisbursedLoan>> loansByBranch = entry.getValue();
            List<DisbursedLoanMonth.BranchDisbursedLoan> branchDisbursedLoans = new ArrayList<>();
            //A for loop to return loans per branch to find total principal and number of loans
            for (Map.Entry<String, List<DisbursedLoan>> branchEntry : loansByBranch.entrySet()) {
                String branch = branchEntry.getKey();
                List<DisbursedLoan> loansOfBranch = branchEntry.getValue();
                BigDecimal totalPrincipal = loansOfBranch.stream()
                        .map(DisbursedLoan::getPrincipal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                int count = loansOfBranch.size();
                DisbursedLoanMonth.BranchDisbursedLoan branchDisbursedLoan = DisbursedLoanMonth.BranchDisbursedLoan.builder()
                        .branch(branch)
                        .numberOfDisbursedLoans(count)
                        .disbursedLoans(loansOfBranch)
                        .totalPrincipal(totalPrincipal)
                        .build();
                branchDisbursedLoans.add(branchDisbursedLoan);
            }
            DisbursedLoanMonth disbursedLoanMonth = DisbursedLoanMonth.builder()
                    .month(month)
                    .branchDisbursedLoans(branchDisbursedLoans)
                    .build();
            disbursedLoanMonths.add(disbursedLoanMonth);
        }

        disbursedLoanMonths.sort(Comparator.comparing(DisbursedLoanMonth::getMonth));
        return disbursedLoanMonths;
    }

    public DisbursedLoans disbursedLoans(List<DisbursedLoanMonth> disbursedLoanMonths) {
        BigDecimal totalPrincipalDisbursed = disbursedLoanMonths.stream()
                .map(DisbursedLoanMonth::getTotalPrincipal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return DisbursedLoans.builder()
                .totalPrincipalDisbursed(totalPrincipalDisbursed)
                .disbursedLoanMonths(disbursedLoanMonths)
                .build();
    }

    public List<DisbursedLoan> getDisbursedLoansByRange(List<Loan> loans, String fromDate, String toDate) {
        List<DisbursedLoan> disbursedLoans = getDisbursedLoans(loans);

        return disbursedLoans.stream()
                .filter(disbursedLoan -> disbursedLoan.getDisbursedAt().isAfter(LocalDate.parse(fromDate)))
                .filter(disbursedLoan -> disbursedLoan.getDisbursedAt().isBefore(LocalDate.parse(toDate)))
                .collect(Collectors.toList());

    }


}
