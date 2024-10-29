package com.untucapital.usuite.utg.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.dto.AllLoans;
import com.untucapital.usuite.utg.dto.client.Client;
import com.untucapital.usuite.utg.dto.client.ClientResponse;
import com.untucapital.usuite.utg.dto.client.ClientSummary;
import com.untucapital.usuite.utg.dto.client.ClientsMobile;
import com.untucapital.usuite.utg.dto.client.loan.AccountData;
import com.untucapital.usuite.utg.dto.client.loan.LoanAccount;
import com.untucapital.usuite.utg.dto.loans.LoanTransaction;
import com.untucapital.usuite.utg.dto.loans.RepaymentScheduleLoan;
import com.untucapital.usuite.utg.dto.loans.SingleLoan;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.PageItems;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.SavingsAccountLoans;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions.PageItemsTrans;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions.SavingsAccountsTransactions;
import com.untucapital.usuite.utg.dto.pastel.PastelTransReq;
import com.untucapital.usuite.utg.exception.LoanListCannotBeNullExceptionHandler;
import com.untucapital.usuite.utg.model.metabase.LoanApiResponse;
import com.untucapital.usuite.utg.model.metabase.LoanData;
import com.untucapital.usuite.utg.model.Employee;
import com.untucapital.usuite.utg.model.transactions.Loans;
import com.untucapital.usuite.utg.model.transactions.PageItem;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.model.transactions.interim.dto.PmfSummaryDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.SavingsTransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionTypeDTO;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Panashe Rutimhu Dell 8/10/2023
 */
@Service
@Slf4j
public class RestClient {

    @Value("${pastel.url}")
    private String pastelUrl;

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

    public SavingsAccountLoans getSavingsLoanAccounts(Long timestamp) {
        log.info("Calling musoni to get loans");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        SavingsAccountLoans loans = new SavingsAccountLoans();

        try {
            String loanString = restTemplate.exchange(baseUrl + "savingsaccounts?modifiedSinceTimestamp=" + timestamp, HttpMethod.GET, entity, String.class).getBody();
            log.info("Loans in the past 24 hours: {}", loanString);
            loans = objectMapper.readValue(loanString, SavingsAccountLoans.class);
            log.info("Loans object: {}", loans);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }
        return loans;
    }

    public Loans getLoans(Long timestamp) {
        log.info("Calling musoni to get loans");
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        Loans loans = new Loans();

        try {
            String loanString = restTemplate.exchange(baseUrl + "loans?modifiedSinceTimestamp=" + timestamp, HttpMethod.GET, entity, String.class).getBody();
            log.info("Loans in the past 24 hours: {}", loanString);
            loans = objectMapper.readValue(loanString, Loans.class);
            log.info("Loans object: {}", loans);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }

        return loans;
    }

    public List<Transactions> getTransactions(int loanId, Long timestamp) throws ParseException, ParseException {

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
                LocalDate transDate = MusoniUtils.formatDate(tx.getSubmittedOnDate());
                Boolean isRequired = MusoniUtils.compareDates(timestamp, transDate);
                if(isRequired) {
                    log.info("TX: {}", tx);
                    cashTransactions.add(tx);
                }
            }
            log.info("Transaction with repayment or disbursement: {}", cashTransactions.toString());
        }
        log.info("Transaction with repayment or disbursement: {}", cashTransactions.toString());
        return cashTransactions;
    }

    public LocalDate getDisbursementDate(List<TransactionDTO> transactions) {
        for (TransactionDTO transaction : transactions) {
            TransactionTypeDTO type = transaction.getType();
            if (type != null && type.getDisbursement()) {
                return transaction.getDate();
            }
        }
        return null;
    }

    public LocalDate getMaturityDate(int loanId) {
        SingleLoan loan = getLoanId(String.valueOf(loanId));

        log.info("maturityDate: {}", loan);

        // Extract the expected maturity date from the timeline
        int[] maturityDateArray = loan.getTimeline().getExpectedMaturityDate();

        if (maturityDateArray != null && maturityDateArray.length == 3) {
            // Convert the array of integers to LocalDate
            LocalDate maturityDate = LocalDate.of(maturityDateArray[0], maturityDateArray[1], maturityDateArray[2]);
            return maturityDate;
        } else {
            // Handle the case where the maturity date is not available or in an unexpected format
            log.warn("Expected maturity date is not available or is in an unexpected format.");
            return null;
        }
    }

    public LocalDate getExpectedFirstRepaymentDate(int loanId) {
        SingleLoan loan = getLoanId(String.valueOf(loanId));

        log.info("expectedFirstRepaymentDate: {}", loan);

        // Extract the expected first repayment date from the timeline
        int[] firstRepaymentDateArray = loan.getExpectedFirstRepaymentOnDate();

        if (firstRepaymentDateArray != null && firstRepaymentDateArray.length == 3) {
            // Convert the array of integers to LocalDate
            LocalDate firstRepaymentDate = LocalDate.of(firstRepaymentDateArray[0], firstRepaymentDateArray[1], firstRepaymentDateArray[2]);
            return firstRepaymentDate;
        } else {
            // Handle the case where the first repayment date is not available or in an unexpected format
            log.warn("Expected first repayment date is not available or is in an unexpected format.");
            return null;
        }
    }


    //    private List<TransactionDTO> transactions;
public TransactionDTO getSavingsBalanceBD(String savingsId, LocalDate localDate, List<SavingsTransactionDTO> transactions) {
    Optional<TransactionDTO> transactionOpt = transactions.stream()
            // Convert SavingsTransactionDTO to TransactionDTO before filtering
            .map(savingsTransaction -> {
                TransactionDTO transactionDTO = new TransactionDTO();
                transactionDTO.setId(savingsTransaction.getId());
                transactionDTO.setType(savingsTransaction.getTransactionType());
                LocalDate date = MusoniUtils.convertToLocalDate(savingsTransaction.getDate());
                transactionDTO.setDate(date);
                transactionDTO.setCurrency(savingsTransaction.getCurrency());
                transactionDTO.setAmount(savingsTransaction.getRunningBalance());
                transactionDTO.setSubmittedByUsername(null); // Set appropriate value if necessary
                // Set other fields as needed
                return transactionDTO;
            })
            // Filter transactions for the specific SavingsId and dates before the provided date
            .filter(t -> t.getDate().isBefore(localDate))
            .max((t1, t2) -> t1.getDate().compareTo(t2.getDate())); // Find the most recent transaction

    // Return the found transaction or handle if not found
    return transactionOpt.orElse(null); // Customize the response for no transaction found
}


    public List<TransactionDTO> getTransactionsByLoanId(int loanId) {
        log.info("Calling Musoni to get transactions for loan ID: {}", loanId);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        List<TransactionDTO> transactions = new ArrayList<>();

        try {
            String transactionsString = restTemplate.exchange(
                    baseUrl + "loans/" + loanId + "?associations=transactions",
                    HttpMethod.GET,
                    entity,
                    String.class
            ).getBody();
            log.info("Transactions for loan ID {}: {}", loanId, transactionsString);

            // Map the "transactions" array from the JSON response to a list of TransactionDTO objects
            JsonNode rootNode = objectMapper.readTree(transactionsString);
            JsonNode transactionsNode = rootNode.path("transactions");

            if (transactionsNode.isArray()) {
                List<TransactionDTO> allTransactions = objectMapper.readValue(
                        transactionsNode.toString(),
                        new TypeReference<List<TransactionDTO>>() {}
                );

                // Filter out transactions that contain the transfer field
                transactions = allTransactions.stream()
                        .filter(transaction -> transaction.getTransfer() == null)
                        .collect(Collectors.toList());
            }

            log.info("Filtered transactions list (without transfer): {}", transactions);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }

        return transactions;
    }


    public List<SavingsTransactionDTO> getTransactionsBySavingsId(int savingsId) {
        log.info("Calling Musoni to get transactions for savings ID: {}", savingsId);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        List<SavingsTransactionDTO> transactions = new ArrayList<>();

        try {
            String transactionsString = restTemplate.exchange(
                    baseUrl + "savingsaccounts/" + savingsId + "?associations=transactions",
                    HttpMethod.GET,
                    entity,
                    String.class
            ).getBody();
            log.info("Transactions for savings ID {}: {}", savingsId, transactionsString);

            // Map the "transactions" array from the JSON response to a list of SavingsTransactionDTO objects
            JsonNode rootNode = objectMapper.readTree(transactionsString);
            JsonNode transactionsNode = rootNode.path("transactions");

            if (transactionsNode.isArray()) {
                // Deserialize the JSON into a list of SavingsTransactionDTO objects
                List<SavingsTransactionDTO> allTransactions = objectMapper.readValue(
                        transactionsNode.toString(),
                        new TypeReference<List<SavingsTransactionDTO>>() {}
                );

                // Filter the transactions where the transaction type value is "Deposit"
                transactions = allTransactions.stream()
                        .filter(transaction ->
                                AppConstants.LOAN_DEPOSIT.equalsIgnoreCase(transaction.getTransactionType().getValue())
                        )
                        .collect(Collectors.toList());
            }

            log.info("Filtered transactions list: {}", transactions);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }

        return transactions;
    }


    public List<TransactionDTO> getTransactionsByPostMaturityFeeId(int loanId) {
        log.info("Calling Musoni to get transactions for loan ID: {}", loanId);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        List<TransactionDTO> transactions = new ArrayList<>();

        try {
            String transactionsString = restTemplate.exchange(baseUrl + "loans/" + loanId + "?associations=charges", HttpMethod.GET, entity, String.class).getBody();
            log.info("Transactions for loan ID {}: {}", loanId, transactionsString);

            // Parse the JSON response
            JsonNode rootNode = objectMapper.readTree(transactionsString);
            JsonNode chargesNode = rootNode.path("charges");
            JsonNode disbursedNode = rootNode.path("summary");
            JsonNode timelineNode = rootNode.path("timeline");

            log.info("chargesNode: {}", chargesNode);

            // Handle charges
            if (chargesNode.isArray()) {
                transactions = objectMapper.readValue(chargesNode.toString(), new TypeReference<List<TransactionDTO>>() {});
            }

            // Modify the transactions for charges
            transactions = transactions.stream()
                    .peek(transaction -> {
                        if (transaction.getLoanSummary() == null) { // This is a charge, not a summary
                            // Set date from charge's dueDate
                            chargesNode.forEach(charge -> {
                                if (charge.has("dueDate") && charge.has("id") && transaction.getId() == charge.get("id").asInt()) {
                                    // Set the date from dueDate
                                    JsonNode dueDateNode = charge.get("dueDate");
                                    if (dueDateNode.isArray() && dueDateNode.size() == 3) {
                                        LocalDate dueDate = LocalDate.of(
                                                dueDateNode.get(0).asInt(),
                                                dueDateNode.get(1).asInt(),
                                                dueDateNode.get(2).asInt()
                                        );
                                        transaction.setDate(dueDate);
                                    }

                                    // Set the transactionType to "PMF " + name
                                    if (charge.has("name")) {
                                        String chargeName = charge.get("name").asText();
                                        TransactionTypeDTO transactionType = new TransactionTypeDTO();
                                        transactionType.setValue("Charges " + chargeName);
                                        transaction.setType(transactionType);
                                    }
                                }
                            });
                        }
                    })
                    .collect(Collectors.toList());

            // Handle the loan summary (disbursement details)
            if (!disbursedNode.isMissingNode() && !timelineNode.isMissingNode()) {
                PmfSummaryDTO loanSummary = objectMapper.readValue(disbursedNode.toString(), PmfSummaryDTO.class);

                // Create a special TransactionDTO to hold the loan summary
                TransactionDTO summaryTransaction = new TransactionDTO();
                summaryTransaction.setLoanSummary(loanSummary);

                // Set the date from actualDisbursementDate in the timeline
                if (timelineNode.has("actualDisbursementDate")) {
                    JsonNode actualDisbursementDateNode = timelineNode.get("actualDisbursementDate");
                    if (actualDisbursementDateNode.isArray() && actualDisbursementDateNode.size() == 3) {
                        LocalDate actualDisbursementDate = LocalDate.of(
                                actualDisbursementDateNode.get(0).asInt(),
                                actualDisbursementDateNode.get(1).asInt(),
                                actualDisbursementDateNode.get(2).asInt()
                        );
                        summaryTransaction.setDate(actualDisbursementDate);
                    }
                }

                // Set the transaction type to "PMF Disbursement" or any other relevant value
                TransactionTypeDTO transactionType = new TransactionTypeDTO();
                transactionType.setValue("Charges");
                summaryTransaction.setType(transactionType);

                // Add the summary transaction to the list of transactions
                transactions.add(summaryTransaction);
            }

            log.info("Modified transactions list: {}", transactions);

        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }

        return transactions;
    }


    public List<SavingsAccountsTransactions> getSavingsAccountsTransactions(int loanId, Long timestamp) throws ParseException, ParseException {

        log.info("PageItem Id :{}", loanId);
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        PageItemsTrans pageItem = new PageItemsTrans();

        try {
            String loanString = restTemplate.exchange(baseUrl + "savingsaccounts/" + loanId + "?associations=transactions", HttpMethod.GET, entity, String.class).getBody();

            log.info("PageItem with id " + loanId + " and transaction information: {}", loanString);

            pageItem = objectMapper.readValue(loanString, PageItemsTrans.class);

            log.info("Transaction information for a loan: {}", pageItem);
        } catch (Exception e) {
            log.info("Failed to get Loans: {}", e.getMessage());
        }

        List<SavingsAccountsTransactions> transactions = pageItem.getTransactions();

        if (transactions == null) {

            return null;
        }

        log.info("Transactions : {}", transactions.toString());

        List<SavingsAccountsTransactions> cashTransactions = new ArrayList<>();
        for (SavingsAccountsTransactions tx : transactions) {
            if (tx.getTransactionType().isDeposit()) {

                LocalDate transDate = MusoniUtils.formatDate(tx.getSubmittedOnDate());
                Boolean isRequired = MusoniUtils.compareDates(timestamp, transDate);

                if(isRequired) {
                    log.info("TX: {}", tx);
                    cashTransactions.add(tx);
                }

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
            String repaymentSchedule = restTemplate.exchange(baseUrl+"/loans/"
                    + loanAccount + "?associations=repaymentSchedule", HttpMethod.GET, setHttpEntity(), String.class).getBody();

            repaymentScheduleLoan = objectMapper.readValue(repaymentSchedule, RepaymentScheduleLoan.class);

            log.info("RepaymentScheduleLoan:{}", repaymentScheduleLoan);

        } catch (Exception e) {
            log.info("Failed to get repayment schedule loans:{}", e.getMessage());
        }

        return repaymentScheduleLoan;
    }

    public String getOverdue(long timestamps){

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange(
                baseUrl + "loans?modifiedSinceTimestamp=" + timestamps,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();
         return timestampedLoanAcc;
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

    public TransactionInfo savePostGlTransaction(PastelTransReq req) throws JsonProcessingException {

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String request = objectMapper.writeValueAsString(req);
        // Create the HTTP entity with the request body and headers
        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);
        TransactionInfo transactionInfo = new TransactionInfo();

        try {

            log.info("Request entity:{}",requestEntity);

            String responseEntity  = restTemplate.exchange(pastelUrl , HttpMethod.POST, requestEntity, String.class).getBody();

            transactionInfo = objectMapper.readValue(responseEntity,TransactionInfo.class);
            log.info("SAVED TRANSACTION: {}", responseEntity);
        }catch(Exception e){
            log.info("FAILED TO SAVE THE TRANSACTION: ", e.getMessage());
        }
        return transactionInfo;
    }


    public String getClientIDByDataFilter(@RequestBody ClientsMobile clientsMobile) {
        final String s = """
                {
                    "filterRulesExpression": {
                        "condition": "OR",
                        "rules": [
                            {
                                "id": "mobileNo",
                                "field": "mobileNo",
                                "type": "string",
                                "input": "text",
                                "operator": "equal",
                                "value": %s
                            },
                            {
                                "id": "mobileNoSecondary",
                                "field": "mobileNoSecondary",
                                "type": "string",
                                "input": "text",
                                "operator": "equal",
                                "value": %s
                            }
                        ],
                        "valid": true
                    },
                    "responseParameters": [
                        {
                            "ordinal": 0,
                            "name": "id"
                        },
                        {
                            "ordinal": 1,
                            "name": "accountNo"
                        },
                        {
                            "ordinal": 2,
                            "name": "status"
                        },
                        {
                            "ordinal": 3,
                            "name": "mobileNo"
                        }
                    ],
                    "sortByParameters": [
                        {
                            "ordinal": 0,
                            "name": "id",
                            "direction": "ASC"
                        }
                    ]
                }
                """.formatted(clientsMobile.getPrimaryMobileNumber(), clientsMobile.getSecondaryMobileNumber());
        String client, clientId;
        try {
            HttpEntity<String> entity = new HttpEntity<String>(s, httpHeaders());
            client = restTemplate.exchange(baseUrl + "datafilters/clients/queries/run-filter", HttpMethod.POST, entity, String.class).getBody();
            clientId = new JSONObject(new JSONObject(client).getJSONArray("pageItems").get(0).toString()).getBigInteger("id").toString();
//            client = getClientById(clientId);

        } catch (JSONException e) {
            return "{ \"errorMessage\": \"Client with mobile number %s not found\"}".formatted(clientsMobile.getPrimaryMobileNumber());
        }
        return clientId;
    }

    public String getSavingsAccountByClientId(Long clientId) {
        final String requestPayload = """
            {
                "filterRulesExpression": {
                    "condition": "AND",
                    "rules": [
                        {
                            "id": "clientId",
                            "field": "clientId",
                            "type": "long",
                            "input": "text",
                            "operator": "equal",
                            "value": %s
                        }
                    ],
                    "valid": true
                },
                "responseParameters": [
                    {
                        "ordinal": 0,
                        "name": "id"
                    },
                    {
                        "ordinal": 1,
                        "name": "accountNo"
                    },
                    {
                        "ordinal": 2,
                        "name": "clientName"
                    },
                    {
                        "ordinal": 3,
                        "name": "status"
                    }
                ],
                "sortByParameters": [
                    {
                        "ordinal": 0,
                        "name": "id",
                        "direction": "ASC"
                    }
                ]
            }
            """.formatted(clientId);

        String response;
        String savingsAccountId;

        try {
            HttpEntity<String> entity = new HttpEntity<>(requestPayload, httpHeaders());
            response = restTemplate.exchange(baseUrl + "datafilters/savingsaccounts/queries/run-filter", HttpMethod.POST, entity, String.class).getBody();

            log.info("response: {}", response);

            // Parse the JSON response
            JSONArray pageItems = new JSONObject(response).getJSONArray("pageItems");
            log.info("page items: {}", pageItems);

            // Initialize a variable to store the desired savings account ID
            savingsAccountId = null;

            // Get the ID from the last object in the pageItems array
            if (pageItems.length() > 0) {
                JSONObject lastItem = pageItems.getJSONObject(pageItems.length() - 1);
                savingsAccountId = String.valueOf(lastItem.getInt("id"));
                log.info("Found savings account ID: {}", savingsAccountId);
            }

            // Handle the case where the desired savings account is not found
            if (savingsAccountId == null) {
                log.info("No savings account matching the criteria was found.");
            } else {
                // Process the savings account ID further as needed
                log.info("Processing savings account ID: {}", savingsAccountId);
            }

        } catch (JSONException e) {
            return String.format("{ \"errorMessage\": \"Savings account with clientId %s not found\"}", clientId);
        }


        return savingsAccountId;
    }

    public String getClientByDateOfBirth(){
        LocalDate localDate = LocalDate.now();
        final String s = """
                {
                    "filterRulesExpression": {
                        "condition": "AND",
                        "rules": [
                            {
                                "id": "dateOfBirth",
                                "field": "dateOfBirth",
                                "type": "date",
                                "input": "date",
                                "operator": "equal",
                                "value": %s
                            },
                             {
                                "id": "status",
                                "field": "status",
                                "type": "integer",
                                "input": "select",
                                "operator": "equal",
                                "value": 300
                             }
                        ],
                        "valid": true
                    },
                    "responseParameters": [
                        {
                            "ordinal": 0,
                            "name": "id"
                        },
                        {
                            "ordinal": 1,
                            "name": "accountNo"
                        },
                        {
                            "ordinal": 2,
                            "name": "mobileNo"
                        },
                        {
                            "ordinal": 3,
                            "name": "dateOfBirth"
                        }
                    ],
                    "sortByParameters": [
                        {
                            "ordinal": 0,
                            "name": "id",
                            "direction": "ASC"
                        }
                    ]
                }
                """.formatted(localDate);

        String clientsFilteredResponse;

        try {
            HttpEntity<String> entity = new HttpEntity<String>(s, httpHeaders());
            clientsFilteredResponse = restTemplate.exchange(baseUrl + "datafilters/clients/queries/run-filter", HttpMethod.POST, entity, String.class).getBody();
        }
        catch (JSONException e) {
            return "{ \"errorMessage\": \"Client with Date of Birth %s not found\"}".formatted(localDate);
        }
        log.info("clientsFilteredResponse: {}",clientsFilteredResponse);
            return clientsFilteredResponse;

    }

    public String getLoansByFilter(int loanStatus, double loanAmount, int dayInArrears){
        loanStatus = 300;
        loanAmount = 10000;
        dayInArrears = 10;
        final String s = """
            {
              "filterRulesExpression": {
                "condition": "AND",
                "rules": [
                  {
                    "id": "status",
                    "field": "status",
                    "type": "integer",
                    "input": "select",
                    "operator": "equal",
                    "value": %s
                  },
                  {
                    "id": "principal",
                    "field": "principal",
                    "type": "double",
                    "input": "select",
                    "operator": "greater",
                    "value": %s
                  }
                ]
              },
              "responseParameters": [
                {
                  "name": "accountNo",
                  "ordinal": 0
                }
                ],
              "sortByParameters": [
                {
                  "ordinal": 0,
                  "name": "accountNo",
                  "direction": "ASC"
                }
              ]
            }
            """.formatted(loanStatus, loanAmount, dayInArrears);

        String loansFilteredResponse;

        try {
            HttpEntity<String> entity = new HttpEntity<String>(s, httpHeaders());
            loansFilteredResponse = restTemplate.exchange(baseUrl + "datafilters/loans/queries/run-filter", HttpMethod.POST, entity, String.class).getBody();
        }
        catch (JSONException e) {
            return "{ \"errorMessage\": \"Loan with selected fields not found\"}";
        }
        log.info("loansFilteredResponse: {}",loansFilteredResponse);
            return loansFilteredResponse;
    }

    public PageItems getSavingsLoanAccountById(@PathVariable String savingsId) {
        PageItems settlementAccount = new PageItems();
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        try {
            String savingsAcc = restTemplate.exchange(baseUrl + "savingsaccounts/"+savingsId, HttpMethod.GET, entity, String.class).getBody();
            settlementAccount = objectMapper.readValue(savingsAcc,PageItems.class);

        }catch(Exception e){
            log.info("FAILED TO GET SETTLEMENT ACCOUNT: ", e.getMessage());
        }
        return settlementAccount;
    }

    public PageItems getClientAccountsByLoanAcc(@PathVariable String loanId) {
        PageItems settlementAccount = new PageItems();
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        try {
            String savingsAcc = restTemplate.exchange(baseUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
            log.info("Savings Acc: {}",savingsAcc);
            settlementAccount = objectMapper.readValue(savingsAcc,PageItems.class);
        }catch(Exception e){
            log.info("FAILED TO GET LOAN ACCOUNT: ", e.getMessage());
        }
        return settlementAccount;
    }

    public PageItems getClientFeesByLoanId(@PathVariable String loanId) {
        PageItems settlementAccount = new PageItems();
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        try {
            String savingsAcc = restTemplate.exchange(baseUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
            log.info("Savings Acc: {}",savingsAcc);
            settlementAccount = objectMapper.readValue(savingsAcc,PageItems.class);
        }catch(Exception e){
            log.info("FAILED TO GET LOAN ACCOUNT: ", e.getMessage());
        }
        return settlementAccount;
    }

    public List<ClientSummary> filterClientsByName(String name) {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());

        try {
            String responseString = restTemplate.exchange(baseUrl + "clients?search=" + name, HttpMethod.GET, entity, String.class).getBody();
            log.info("Clients filtered by name: {}", responseString);

            // Map the JSON response to the wrapper class
            ClientResponse clientResponse = objectMapper.readValue(responseString, ClientResponse.class);

            // Map each Client to ClientSummary with only specified fields
            List<ClientSummary> clientSummaries = clientResponse.getPageItems().stream().map(client -> {
                ClientSummary summary = new ClientSummary();
                summary.setId(client.getId());
                summary.setStatus(client.getStatus().getValue());
                summary.setActivationDate(formatDate(client.getActivationDate()));
                summary.setFirstname(client.getFirstname());
                summary.setLastname(client.getLastname());
                summary.setDisplayName(client.getDisplayName());
                summary.setMobileNo(client.getMobileNo());
                summary.setDateOfBirth(formatDate(client.getDateOfBirth()));
                summary.setGender(client.getGender().getName());
                summary.setOfficeName(client.getOfficeName());
                return summary;
            }).collect(Collectors.toList());

            log.info("Client summary list: {}", clientSummaries);
            return clientSummaries;
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
            return Collections.emptyList(); // Return an empty list in case of an error
        }
    }

    // Helper method to format date from either List<Integer> or int[]
    private String formatDate(Object date) {
        if (date instanceof List) {
            List<Integer> dateList = (List<Integer>) date;
            return dateList.size() == 3 ? String.format("%04d-%02d-%02d", dateList.get(0), dateList.get(1), dateList.get(2)) : null;
        } else if (date instanceof int[]) {
            int[] dateArray = (int[]) date;
            return dateArray.length == 3 ? String.format("%04d-%02d-%02d", dateArray[0], dateArray[1], dateArray[2]) : null;
        }
        return null;
    }




    public List<LoanData> saveMusoniLoansToUtg() {
        // Make the API call and get the response mapped to LoanApiResponse
        LoanApiResponse response = restTemplate.exchange(baseUrl + "loans?limit=5&offset=0",
                HttpMethod.GET,
                setHttpEntity(),
                new ParameterizedTypeReference<LoanApiResponse>() {}).getBody();

        log.info("API response: {}", response);

        // Check if the response and pageItems are not null
        if (response != null && response.getPageItems() != null) {
            List<LoanData> musoniLoans = response.getPageItems();
            log.info("musoniLoans: {}", musoniLoans);
            return musoniLoans;
        }

        throw new LoanListCannotBeNullExceptionHandler("Loan list cannot be null");
    }


    public List<LoanAccount> getClientLoansById(@PathVariable Long clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        AccountData accountData = new AccountData();
        List<LoanAccount> activeLoanAccounts = new ArrayList<>();
        try {
            String clientLoans = restTemplate.exchange(baseUrl + "clients/"+clientId+"/accounts", HttpMethod.GET, entity, String.class).getBody();

            accountData = objectMapper.readValue(clientLoans, AccountData.class);

            List<LoanAccount> loanAccounts = accountData.getLoanAccounts();

            // Filter loan accounts where status.active is true
            activeLoanAccounts = loanAccounts.stream()
                    .filter(loanAccount -> loanAccount.getStatus().isActive())
                    .collect(Collectors.toList());


        }catch (Exception e){
            log.info("Failed to get Client loans: {}", e.getMessage());
        }

        return activeLoanAccounts;
    }

    public static String generateUniqueId(String prefix) {
        long timestamp = System.currentTimeMillis();
        String uniqueId = prefix + Long.toString(timestamp) + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return uniqueId;
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
