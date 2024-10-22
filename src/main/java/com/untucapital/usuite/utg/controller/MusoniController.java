
package com.untucapital.usuite.utg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itextpdf.io.IOException;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.dto.DisbursedLoans;
import com.untucapital.usuite.utg.dto.FilteredLoans;
import com.untucapital.usuite.utg.dto.LoansIds;
import com.untucapital.usuite.utg.dto.client.ClientsMobile;
import com.untucapital.usuite.utg.dto.client.ViewClientLoansResponse;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.NextInstalmentResponse;
import com.untucapital.usuite.utg.dto.loans.Result;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.ClientAccounts;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.PageItems;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.SettlementAccountResponse;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.SavingsTransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.service.PostGlService;
import com.untucapital.usuite.utg.service.SmsService;
import com.untucapital.usuite.utg.service.metabse.MusoniLoansService;
import com.untucapital.usuite.utg.service.pdfGeneratorService.LoanStatementPdfGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryHint;
import javax.security.auth.login.AccountNotFoundException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@RestController
@RequestMapping(value ="musoni", produces="application/json")
@RequiredArgsConstructor
public class MusoniController {


    private static final Logger log = LoggerFactory.getLogger(MusoniPastelController.class);
    @Autowired
    RestTemplate restTemplate;

    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    private final PostGlService postGlService;

    @Autowired
    private LoanStatementPdfGeneratorService loanStatementPdfGeneratorService;

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(musoniUsername,musoniPassword);
        headers.set("X-Fineract-Platform-TenantId",musoniTenantId);
        headers.set("x-api-key",musoniApiKey);
        headers.set("Content-Type", "application/json");

        return headers;
    }

    private HttpEntity<String> setHttpEntity() {
        return new HttpEntity<String>(httpHeaders());
    }

    private final MusoniService musoniService;
    private final MusoniLoansService musoniLoansService;
    private final SmsService smsService;
    private final RestClient restClient;

    private HttpHeaders headers;

    //    Get All Clients
    @GetMapping("clients")
    public String getAllClients() {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients", HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("postGl")
    public List<PostGLResponseDTO> getAllPostGl() {

        return postGlService.getAllPostGl();
    }

    //Get Client By Id
    @GetMapping("clients/{clientId}")
    public String getClientById(@PathVariable String clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+clientId, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Client By Status
    @GetMapping("clients/status/{status}")
    public String getClientsByStatus(@PathVariable String status) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+status, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Loan By Id
    @GetMapping("loans/{loanId}")
    public String getLoanById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/"+loanId, HttpMethod.GET, entity, String.class).getBody();
    }


    @GetMapping("loans/modifiedSinceTimestamp/{timeStamp}")
    public String getLoanByTimeStamp(@PathVariable Long timeStamp) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?modifiedSinceTimestamp="+timeStamp, HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping("loans/transactions")
    public ResponseEntity <Void> getTransactionsByTimestamp() throws ParseException, JsonProcessingException, AccountNotFoundException {
        musoniService.getLoansByTimestamp();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("sendAutomatedSms")
    public ResponseEntity <Void> sendAutomatedSMS() throws ParseException, JsonProcessingException, AccountNotFoundException {
//        musoniService.sendAutomatedSMS();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("loanTransactions/{loanAccount}")
    public List<TransactionDTO> getTransactionsByLoanId(@PathVariable int loanAccount) throws ParseException, JsonProcessingException, AccountNotFoundException {
        List<TransactionDTO> transactionDTO = musoniService.getTransactionsByLoanId(loanAccount);
        return transactionDTO;
    }

    @GetMapping("savingsTransactions/{savingsAccount}")
    public List<SavingsTransactionDTO> getTransactionsBySavingsId(@PathVariable int savingsAccount) throws ParseException, JsonProcessingException, AccountNotFoundException {
        List<SavingsTransactionDTO> transactionDTO = musoniService.getTransactionsBySavingsId(savingsAccount);
        return transactionDTO;
    }

    @GetMapping("pmfTransactions/{pmfAccount}")
    public List<TransactionDTO> getTransactionsByPostMaturityFeeId(@PathVariable int pmfAccount) throws ParseException, JsonProcessingException, AccountNotFoundException {
        List<TransactionDTO> transactionDTO = musoniService.getTransactionsByPostMaturityFeeId(pmfAccount);
        return transactionDTO;
    }

    @GetMapping("savings/transactions")
    public ResponseEntity <Void> getSavingsLoanAccountsByTimestamp() throws ParseException, JsonProcessingException, AccountNotFoundException {
        musoniService.getSavingsLoanAccountsByTimestamp();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("savingsAccounts/savingsTransactions")
    public ResponseEntity <Void> getSavingsAccountsTransactionsByTimestamp() throws ParseException, JsonProcessingException, AccountNotFoundException {
        musoniService.getSavingsLoanAccountsByTimestamp();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("savingsById/{savingsId}")
    public SettlementAccountResponse getSavingsLoanAccountById(@PathVariable String savingsId) throws AccountNotFoundException {
        return musoniService.getSavingsLoanAccountById(savingsId);
    }

    @GetMapping("getClientIdBySettlementAcc/{savingsId}")
    public SettlementAccountResponse getClientIdBySettlementAcc(@PathVariable String savingsId) throws AccountNotFoundException {
        return musoniService.getClientIdBySettlementAcc(savingsId);
    }

    @GetMapping("getClientAccountsByLoanAcc/{loanAcc}")
    public ClientAccounts getClientAccountsByLoanAcc(@PathVariable String loanAcc) throws AccountNotFoundException, ParseException {
        return musoniService.getClientAccountsByLoanAcc(loanAcc);
    }

    @GetMapping("getClientFeesByLoanId/{loanAcc}")
    public PageItems getClientFeesByLoanId(@PathVariable String loanAcc) throws AccountNotFoundException {
        return restClient.getClientFeesByLoanId(loanAcc);
    }

    public static String[] getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String endDate = dateFormat.format(cal.getTime());
        System.out.println("Current Date Time : " + endDate);
        cal.add(Calendar.YEAR, -1);
        String startDate = dateFormat.format(cal.getTime());
        System.out.println("Subtract one year from current date : " + startDate);

        return new String[] {startDate, endDate};

    }

    //Get All Loans
    @GetMapping("loans")
    @QueryHints(value = {
            @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE),
            @QueryHint(name = HINT_CACHEABLE, value = "false"),
            @QueryHint(name = READ_ONLY, value = "true")
    })

    public String getLoans() {
        String[] dates = getDate();
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans?disbursementFromDate=" + dates[0] + "&disbursementToDate=" + dates[1] + "&limit=2000&orderBy=id&sortOrder=DESC", HttpMethod.GET, entity, String.class).getBody();
    }

    //    Collect Transaction from Musoni
    @GetMapping("/getTransations/loanid/{loanId}/transactionId/{transactionId}")
    public String getMusoniPastelTrans(@PathVariable("loanId") String loanId, @PathVariable("transactionId") String transactionId) {

        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/" +loanId + "/transactions/" + transactionId, HttpMethod.GET, entity, String.class).getBody();
    }


    //Get All Client Loans By Id
    @GetMapping("clientAccounts/{clientId}")
    public String getClientLoansById(@PathVariable Long clientId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients/"+clientId+"/accounts", HttpMethod.GET, entity, String.class).getBody();
    }

//    this is used my mobile app to get active loans using client id
    @GetMapping("getActiveClientLoansByClientId/{clientId}")
    public String getActiveClientLoansByClientId(@PathVariable Long clientId) {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                musoniUrl + "clients/" + clientId + "/accounts", HttpMethod.GET, entity, String.class);

        // Parse the response body
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            ArrayNode loanAccounts = (ArrayNode) root.get("loanAccounts");

            // Filter for active loan accounts
            ArrayNode activeLoanAccounts = mapper.createArrayNode();
            for (JsonNode loanAccount : loanAccounts) {
                if ("Active".equals(loanAccount.get("status").get("value").asText())) {
                    activeLoanAccounts.add(loanAccount);
                }
            }

            // Create the final JSON object with only active loan accounts
            ObjectNode filteredResult = mapper.createObjectNode();
            filteredResult.set("loanAccounts", activeLoanAccounts);
            filteredResult.set("savingsAccounts", root.get("savingsAccounts"));

            // Convert the filtered result to a JSON string and return
            return filteredResult.toString();
        } catch (IOException | JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }


    //Get All Client Loans By Id
    @GetMapping("getActiveClientLoans/{clientId}")
    public List<ViewClientLoansResponse> getClientLoans(@PathVariable Long clientId) throws ParseException {

        return musoniService.activeClientLoans(clientId);
    }

    @GetMapping("getNextInstalment/{loanId}")
    public NextInstalmentResponse getNextInstalment(@PathVariable String loanId) throws ParseException {

        return musoniService.getNextRepaymentSchedule(loanId);
    }



    //Get Loan Repayment Schedule By Id
    @GetMapping("loansRepaymentSchedule/{loanId}")
    public String getLoanLoanRepaymentScheduleById(@PathVariable Long loanId) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "loans/"+loanId+"?associations=repaymentSchedule", HttpMethod.GET, entity, String.class).getBody();
    }

    //Get Clients List By Search Filter
    @GetMapping("clientsSearchFilter/{searchFilter}")
    public String getClientListBySearchFilter(@PathVariable String searchFilter) {
        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders());
        return restTemplate.exchange(musoniUrl + "clients?search="+searchFilter, HttpMethod.GET, entity, String.class).getBody();
    }

    //Get ClientID by PhoneNumber
    @PostMapping("getClientByMobile")
    public String getClientIDByMobile(@RequestBody ClientsMobile clientsMobile) {

        return restClient.getClientIDByDataFilter(clientsMobile);

//        CLIENT ID

    }

    List<String> clientAccounts = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getClientLoans/{clientLoans}")
    public String getClientLoans(@PathVariable String clientLoans) throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "clients/" + clientLoans + "/accounts", HttpMethod.GET, entity, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);
        JsonNode loanAccountsArray = clientAccountJson.get("loanAccounts");

        List<String> clientAccounts = new ArrayList<>();
        for (JsonNode loanAccount : loanAccountsArray) {
            String accountNo = loanAccount.get("accountNo").asText();
            clientAccounts.add(accountNo);
        }

        StringBuilder menu = new StringBuilder("");
        int num = 1;
        for (String account : clientAccounts) {
            menu.append("\n").append(num).append(". ").append(account);
            num++;
        }

        return menu.toString();
    }


    List<String> loanAcc = new ArrayList<>();
    //Get Clients Loans By Client ID
    @GetMapping("getLoanBalance/{loanAccount}")
    public Object getLoanBalance(@PathVariable String loanAccount) throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String clientAccount = restTemplate.exchange(musoniUrl + "loans/" + loanAccount, HttpMethod.GET, entity, String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);

        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);

        String accountNo = clientAccountJson.get("accountNo").asText();
        String principalDisbursed = clientAccountJson.at("/summary/principalDisbursed").asText();
        String amountPaid = clientAccountJson.at("/summary/principalPaid").asText();
        String amountOverdue = clientAccountJson.at("/summary/principalOverdue").asText();
        String disbursmentDate = String.format(
                "%s-%s-%s",
                clientAccountJson.at("/timeline/actualDisbursementDate/2").asText(),
                clientAccountJson.at("/timeline/actualDisbursementDate/1").asText(),
                clientAccountJson.at("/timeline/actualDisbursementDate/0").asText()
        );
        String status = clientAccountJson.at("/status/value").asText();
        String numberOfRepayments = clientAccountJson.get("numberOfRepayments").asText();
        String maturityDate = String.format(
                "%s-%s-%s",
                clientAccountJson.at("/timeline/expectedMaturityDate/2").asText(),
                clientAccountJson.at("/timeline/expectedMaturityDate/1").asText(),
                clientAccountJson.at("/timeline/expectedMaturityDate/0").asText()
        );

        Map<String, Object> loanBal = new HashMap<>();
        loanBal.put("Mini Statement for Loan Account", accountNo);
        loanBal.put("Loan Status", status);
        loanBal.put("Loan Amount Disbursed", principalDisbursed);
        loanBal.put("Disbursment Date", disbursmentDate);
        loanBal.put("Amount Paid", amountPaid);
        loanBal.put("Amount Overdue", amountOverdue);
        loanBal.put("No. of Repayments", numberOfRepayments);
        loanBal.put("Maturity Date", maturityDate);

        return loanBal;
    }

//    List<String> loanAccRepay = new ArrayList<>();
    //Get Clients Loans By Client ID
//    @GetMapping("getLoanRepaymentSchedule/{loanAccount}")
//    public Object getLoanRepaymentSchedule(@PathVariable String loanAccount) throws JsonProcessingException {
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
//        String clientAccount = restTemplate.exchange(
//                musoniUrl + "loans/" + loanAccount + "?associations=repaymentSchedule",
//                HttpMethod.GET,
//                entity,
//                String.class
//        ).getBody();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);
//        JsonNode repaymentSchedule = clientAccountJson.at("/repaymentSchedule/periods");
//
//        List<Map<String, Object>> loanAccRepay = new ArrayList<>();
//
//        if (repaymentSchedule.isArray()) {
//            for (JsonNode periodNode : repaymentSchedule) {
//                String period = getNodeText(periodNode, "period");
//                String fromDate = formatDate(periodNode.get("fromDate"));
//                String dueDate = formatDate(periodNode.get("dueDate"));
//                String amountDue = getNodeText(periodNode, "totalDueForPeriod");
//                String amountPaid = getNodeText(periodNode, "totalPaidForPeriod");
//                String amountOutstanding = getNodeText(periodNode, "totalOutstandingForPeriod");
//                String paidBy = formatDate(periodNode.get("obligationsMetOnDate"));
//
//                Map<String, Object> loanBal = new HashMap<>();
//                loanBal.put("loanId", loanAccount);
//                loanBal.put("period", period);
////                loanBal.put("From Date", fromDate);
//                loanBal.put("date", dueDate);  // 1st column
//                loanBal.put("totalDue", amountDue); //3rd column
//                loanBal.put("totalPaid", amountPaid); //4th column
//                loanBal.put("totalOutstanding", amountOutstanding); //5th column
//                loanBal.put("paidBy", paidBy); //2nd column
//
//                loanAccRepay.add(loanBal);
//            }
//        }
//
//        return loanAccRepay;
//    }

    @GetMapping("getLoanRepaymentSchedule/{loanAccount}")
    public Object getLoanRepaymentSchedule(@PathVariable String loanAccount) throws JsonProcessingException {
//        return musoniService.getLoanRepaymentSchedule(loanAccount);
        return restClient.getRepaymentSchedule(loanAccount);
    }

    @GetMapping("getAndProcessLoanRepayment/{loanAccount}")
    public ResponseEntity<List<TransactionDTO>> getAndProcessLoanRepayment(@PathVariable String loanAccount) {
        try {
            // Call the service method to get and process the loan repayment schedule
            List<TransactionDTO> result = musoniService.getAndProcessLoanRepayment(loanAccount);

            // Return the result with an HTTP 200 OK status
            return ResponseEntity.ok(result);
        } catch (JsonProcessingException e) {
            // Handle the exception and return an HTTP 500 Internal Server Error status
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getMusoniLoanRepaymentSchedulePdf/{loanAccount}")
    public ResponseEntity<byte[]> getLoanRepaymentSchedulePdf(@PathVariable String loanAccount) throws java.io.IOException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        String clientAccount = restTemplate.exchange(
                musoniUrl + "loans/" + loanAccount + "?associations=repaymentSchedule",
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode clientAccountJson = objectMapper.readTree(clientAccount);
        JsonNode repaymentSchedule = clientAccountJson.at("/repaymentSchedule/periods");

        List<Map<String, Object>> loanAccRepay = new ArrayList<>();

        if (repaymentSchedule.isArray()) {
            for (JsonNode periodNode : repaymentSchedule) {
                String period = periodNode.path("period").asText();
                String dueDate = periodNode.path("dueDate").asText();
                String amountDue = periodNode.path("totalDueForPeriod").asText();
                String amountPaid = periodNode.path("totalPaidForPeriod").asText();
                String amountOutstanding = periodNode.path("totalOutstandingForPeriod").asText();
                String paidBy = periodNode.path("obligationsMetOnDate").asText();

                Map<String, Object> loanBal = new HashMap<>();
                loanBal.put("date", dueDate);
                loanBal.put("totalDue", amountDue);
                loanBal.put("totalPaid", amountPaid);
                loanBal.put("totalOutstanding", amountOutstanding);
                loanBal.put("paidBy", paidBy);

                loanAccRepay.add(loanBal);
            }
        }

        byte[] pdfBytes = loanStatementPdfGeneratorService.generateLoanSchedulePdf(loanAccRepay);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_PDF);
        responseHeaders.setContentDispositionFormData("attachment", "LoanSchedule.pdf");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(pdfBytes);
    }
    private String getNodeText(JsonNode node, String fieldName) {
        JsonNode childNode = node.get(fieldName);
        return childNode == null || childNode.isNull() ? "" : childNode.asText();
    }

    private String formatDate(JsonNode dateNode) {
        if (dateNode == null || !dateNode.isArray()) {
            return "";
        }
        int year = dateNode.get(0).asInt();
        int month = dateNode.get(1).asInt();
        int day = dateNode.get(2).asInt();
        return String.format("%04d-%02d-%02d", year, month, day);
    }


//    private String formatDate(JsonNode dateNode) {
//        String year = dateNode.get(2).asText();
//        String month = dateNode.get(1).asText();
//        String day = dateNode.get(0).asText();
//        return year + "-" + month + "-" + day;
//    }


    //    Get Loans By TimeStamp
    List<String> timestampedLoanAccs = new ArrayList<>();
    //    @GetMapping("getLoansByTimestamp/{timestampa}")
    @GetMapping("getLoansByTimestamp")
    public Object getLoansByTimestamp() throws JsonProcessingException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long stamps = timestamp.getTime();
        String stampString = String.valueOf(stamps);
        String stamp = stampString.substring(0, stampString.length() - 3);

        long timestamps = Long.valueOf(stamp) - 5864286L; // 2 months, 1 week is: 5864286
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders());
        String timestampedLoanAcc = restTemplate.exchange(
                musoniUrl + "loans?modifiedSinceTimestamp=" + timestamps,
                HttpMethod.GET,
                entity,
                String.class
        ).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode loanAccJson = objectMapper.readTree(timestampedLoanAcc);
        JsonNode pageItems = loanAccJson.at("/pageItems");

        List<Map<String, Object>> timestampedLoanAccs = new ArrayList<>();

        for (JsonNode pageItem : pageItems) {
            String loan_id = pageItem.get("id").asText();
            String status = pageItem.at("/status/active").asText();
            String client_id = pageItem.get("clientId").asText();
            String days_in_arrears = null;

            if (pageItem.has("summary") && pageItem.at("/summary").has("daysInArrears")) {
                days_in_arrears = pageItem.at("/summary/daysInArrears").asText();
            }

            String phone_number = "0";

            String clientByIdResponse = getClientById(client_id); // Assuming getClientById returns a JSON string
            JsonNode clientById = objectMapper.readTree(clientByIdResponse);

            if (clientById.has("mobileNo")) {
                phone_number = clientById.get("mobileNo").asText();
            }

            if ("true".equals(status) && days_in_arrears != null && !days_in_arrears.isEmpty()) {
                int daysInArrears = Integer.parseInt(days_in_arrears);

                if (phone_number != "0") {
                    String sms_par_one = "";

                    if (daysInArrears == 1) {
                        sms_par_one = "Your repayment amount is due and payable. Please make necessary arrangements to pay so that you maintain a good record of your account. Kindly ignore this message if you have already made the FULL payment.";
                    } else if (daysInArrears == 14) {
                        sms_par_one = "We have not received your installment payment in full, and this is now 14 days in arrears. Please urgently make payment to avoid downgrading of your account and unnecessary penalties.";
                    } else if (daysInArrears == 30) {
                        sms_par_one = "It is now 30 days without full payment of your installment. To avoid being blacklisted and litigation, please make urgent arrangements to settle the account immediately.";
                    }

                    if (!sms_par_one.isEmpty()) {
                        smsService.sendSingle(phone_number, sms_par_one);
                    }
                }
            } else {
                System.out.println("Client has no PAR or Phone number is not available.");
            }

            Map<String, Object> loanObject = new HashMap<>();
            loanObject.put("loanId", loan_id);
            loanObject.put("statusActive", status);
            loanObject.put("daysInArrears", days_in_arrears);
            loanObject.put("clientId", client_id);
            loanObject.put("phoneNumber", phone_number);

            System.out.println(loan_id);
            timestampedLoanAccs.add(loanObject);
        }

        return timestampedLoanAccs;
    }

    public String currencyFormatter(String amount) {
        Locale usa = new Locale("en", "US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(usa);
        return currency.format(amount);
    }

    @GetMapping("getLoansByDisbursementDate/{fromDate}/{toDate}")
    public Result getLoansByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) {
        return  musoniService.disbursedLoans(fromDate,toDate);
    }

    @GetMapping("getLoanIdsByDisbursementDate/{fromDate}/{toDate}")
    public LoansIds getLoanIdsByDisbursementDate(@PathVariable String fromDate, @PathVariable String toDate) {
        return  musoniService.getLoanIdsByDisbursementDate(fromDate,toDate);
    }

    @GetMapping("getLoansMaturityInterestReport/{fromDate}/{toDate}")
    public LoansIds getLoansMaturityInterestReport(@PathVariable String fromDate, @PathVariable String toDate) {
        return  musoniService.getLoansMaturityInterestReport(fromDate,toDate);
    }

    @GetMapping("loans/disbursed-by-range/{fromDate}/{toDate}")
    public DisbursedLoans getLoansDisbursedByDateRange(@PathVariable String fromDate, @PathVariable String toDate) {
        log.info(String.valueOf(musoniService));
        return musoniService.getLoansDisbursedByDateRange(fromDate, toDate);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("loans/disbursed-by-range/{branchName}/{fromDate}/{toDate}")
    public DisbursedLoans findDisbursedLoansByRangeAndBranch(@PathVariable String branchName, @PathVariable String fromDate, @PathVariable String toDate) {
        log.info(String.valueOf(musoniService));
        return musoniService.findDisbursedLoansByRangeAndBranch(branchName, fromDate, toDate);
    }

//    @GetMapping("getClientsByDob/{dob}")
//    @Operation(summary = "Get a client by dob (Date Format: YYYY-MM-DD")
//    public String getClientByDateOfBirth(@PathVariable String dob) {
//        return restClient.getClientByDateOfBirth(dob);
//    }

    @GetMapping("/getClientsByDob/{dob}")
    @Operation(summary = "Get a client by dob (Date Format: YYYY-MM-DD)")
    public String getClientByDateOfBirth(@PathVariable String dob) {
        return musoniService.getClientByDateOfBirth();
    }

    @GetMapping("/getLoansByFilter/{loanStatus}/{loanAmount}/{dayInArrears}")
    @Operation(summary = "Get Filtered loans")
    public List<FilteredLoans> getLoansByFilter(@PathVariable String loanStatus, @PathVariable String loanAmount, @PathVariable String dayInArrears) {

        // Parse the input parameters
        int status = Integer.parseInt(loanStatus);
        double amount = Double.parseDouble(loanAmount);
        int days = Integer.parseInt(dayInArrears);

        // Call the service to get eligible loans
        List<FilteredLoans> filteredLoans = musoniService.getEligibleLoans(status, amount, days);

        return filteredLoans;
    }


}
