package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.BulkEmail;
import com.google.gson.Gson;
import com.untucapital.usuite.utg.dto.Email;
import com.untucapital.usuite.utg.dto.LoanApplicationResponse;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.ClientLoanService;
import com.untucapital.usuite.utg.service.CreditCheckService;
import com.untucapital.usuite.utg.utils.EmailSender;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping(path = "credit_application")
//@RequiredArgsConstructor
public class ClientLoanController {


    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);
    private final EmailSender emailSender;

    @Autowired
    ClientRepository clientRepository;

    private final ClientLoanApplication clientLoanApplication;

    private final CreditCheckService creditCheckService;

    private final ClientLoanService clientLoanService;

    public ClientLoanController(EmailSender emailSender, ClientLoanApplication clientLoanApplication, CreditCheckService creditCheckService, ClientLoanService clientLoanService) {
        this.emailSender = emailSender;
        this.clientLoanApplication = clientLoanApplication;
        this.creditCheckService = creditCheckService;
        this.clientLoanService = clientLoanService;
    }


    @PostMapping
    @Operation(summary = "Create a new client loan application")
    public ResponseEntity<LoanApplicationResponse> saveClientLoan(@RequestBody ClientLoan clientLoan) throws ParseException {
        log.info(String.valueOf(clientLoan));

        // Process the client loan to set default values
        clientLoan = clientLoanService.processClientLoan(clientLoan);

        LoanApplicationResponse response = clientLoanApplication.saveClientLoan(clientLoan);

        // Determine the HTTP status based on the outcome
        HttpStatus status = response.getClientLoan() != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        log.info("response: {} and status: {}", response.getMessage(), status);

        // Returning the response as a JSON object with appropriate status
        return new ResponseEntity<>(response, status);
    }



//    @PostMapping
//    @Operation(summary = "Create a new client loan application")
//    public ResponseEntity<LoanApplicationResponse> saveClientLoan(@RequestBody ClientLoan clientLoan) throws ParseException {
//        log.info(String.valueOf(clientLoan));
//        // Process the client loan to set default values
//        clientLoan = clientLoanService.processClientLoan(clientLoan);
//        return new ResponseEntity<>(clientLoanApplication.saveClientLoan(clientLoan), HttpStatus.CREATED);
//    }



    //Fetch for Client FCB
    @PostMapping("/creditCheckedLoan/{id}")
    @Operation(summary = "Check FCB reports for client loan application")
    public ResponseEntity<ClientLoan> creditCheckedLoan(@PathVariable String id) throws ParseException {

        ClientLoan clientLoan = creditCheckService.fetchFCBCreditStatusById(id);
        if (clientLoan != null) {
            return new ResponseEntity<>(clientLoan, HttpStatus.CREATED);
        }else {
            return null;
        }
    }


    @GetMapping("getActiveLoanByUserId/{userId}")
    @Operation(summary = "get a new client loan application")
    public List<ClientLoan> getActiveLoanByUserId(@PathVariable("userId") String userId) {

        return clientLoanApplication.getActiveLoans(userId);

    }

    @GetMapping("getLoansWhereUserExistsInMusoni")
    @Operation(summary = "Get loans by username where username is an Integer")
    public List<ClientLoan> getLoansWhereUserExistsInMusoni() {
        return clientLoanApplication.getLoansByUsername();
    }



    @GetMapping("userByPhone/{phone}")
    @Operation(summary = "get a new client loan application")
    public ClientLoan getClientLoanApplicationByMobile(@PathVariable("phone") String phone) {

        return clientLoanApplication.getClientLoanApplicationByMobile(phone);

    }

    //build get all loan applications REST API
    @GetMapping
    @Operation(summary = "Get all client loan applications")
    public List<ClientLoan> getAllClientLoanApplication() {
        return clientLoanApplication.getAllClientLoanApplication();

    }

    @GetMapping("/recent")
    public List<ClientLoan> getRecentClientLoans() {
        return clientLoanApplication.getRecentClientLoans();
    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    @Operation(summary = "Get a client loan application by ID")
    public ResponseEntity<ClientLoan> getClientLoanApplicationById(@PathVariable("id") String clientloanID) {
        return new ResponseEntity<ClientLoan>(clientLoanApplication.getClientLoanApplicationById(clientloanID), HttpStatus.OK);
    }

    @GetMapping("/caseloads")
    @Operation(summary = "Get data on client loans")
    public List<List<Object>> getAllClientLoansData(){
        List<ClientLoan> clientLoanList = new ArrayList<>(clientRepository.findAll());
        Map<LocalDate, Integer> countMap = new TreeMap<>();

        for (ClientLoan clientLoan : clientLoanList) {
            LocalDate createdAtDate = clientLoan.getCreatedAt().toLocalDate();
            countMap.put(createdAtDate, countMap.getOrDefault(createdAtDate, 0) + 1);
        }

        List<List<Object>> data = new ArrayList<>();

        for (Map.Entry<LocalDate, Integer> entry : countMap.entrySet()) {
            List<Object> dataEntry = new ArrayList<>();
            long createdAtMillis = entry.getKey().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            int count = entry.getValue();

            dataEntry.add(createdAtMillis);
            dataEntry.add(count);
            data.add(dataEntry);
        }

        return data;
    }

    @GetMapping("/caseloadsByBranch/{branch}")
    @Operation(summary = "Get data on client loans by branch")
    public List<List<Object>> getAllClientLoansDataByBranch(@PathVariable String branch) throws ParseException {
        List<ClientLoan> clientLoanList = new ArrayList<>(clientRepository.findClientLoansByBranchNameOrderByCreatedAtDesc(branch));
        Map<LocalDate, Integer> countMap = new TreeMap<>();

        for (ClientLoan clientLoan : clientLoanList) {
            LocalDate createdAtDate = clientLoan.getCreatedAt().toLocalDate();
            countMap.put(createdAtDate, countMap.getOrDefault(createdAtDate, 0) + 1);
        }

        List<List<Object>> data = new ArrayList<>();

        for (Map.Entry<LocalDate, Integer> entry : countMap.entrySet()) {
            List<Object> dataEntry = new ArrayList<>();
            long createdAtMillis = entry.getKey().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
            int count = entry.getValue();

            dataEntry.add(createdAtMillis);
            dataEntry.add(count);
            data.add(dataEntry);
        }

        return data;
    }


//    @GetMapping("/caseloadsByLoanCount/{loanCount}")
//    public String getAllClientLoansDataByLoanCount(@PathVariable String loanCount) throws JSONException, ParseException {
//        List<ClientLoan> clientLoanList = new ArrayList<>(clientRepository.findClientLoansByLoanCount(loanCount));
//        Map<LocalDate, Integer> countMap = new TreeMap<>();
//
//        for (ClientLoan clientLoan : clientLoanList) {
//            LocalDate createdAtDate = clientLoan.getCreatedAt().toLocalDate();
//            countMap.put(createdAtDate, countMap.getOrDefault(createdAtDate, 0) + 1);
//        }
//
//        List<List<Object>> data = new ArrayList<>();
//
//        for (Map.Entry<LocalDate, Integer> entry : countMap.entrySet()) {
//            List<Object> dataEntry = new ArrayList<>();
//            long createdAtMillis = entry.getKey().atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
//            int count = entry.getValue();
//
//            dataEntry.add(createdAtMillis);
//            dataEntry.add(count);
//            data.add(dataEntry);
//        }
//
//// Create a JSON array
//        JSONArray jsonArray = new JSONArray(data);
//
//// Convert the JSON array to a string
//        String json = jsonArray.toString();
//
//        return json;
//
//
//    }

    // show BM all loans with checked status
    @GetMapping("/loanStatus/{loanStatus}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusOrderByCreatedAtDesc(loanStatus), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByUserIdOrderByCreatedAtDesc(userId), HttpStatus.OK);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByUserId(@PathVariable("userId") String userId) {
//        List<ClientLoan> userClientLoans = clientLoanApplication.getClientLoanApplicationsByUserId(userId);
//        return ResponseEntity.ok(userClientLoans);
//    }

    @GetMapping("/loanStatus/{loanStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndBranchName(
            @PathVariable("loanStatus") String loanStatus,
            @PathVariable("branchName") String branchName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size); // Set the limit to 20 records
        List<ClientLoan> clientLoans = clientRepository.findClientLoansByLoanStatusAndBranchNameOrderByCreatedAtDesc(
                loanStatus, branchName, pageable);
        return new ResponseEntity<>(clientLoans, HttpStatus.OK);
    }

    // show BM all loans with checked status
    @GetMapping("/loanStatus/{loanStatus}/{assignTo}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndAssignToAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchNameOrderByCreatedAtDesc(loanStatus,assignTo, branchName), HttpStatus.OK);
    }

    // show BM all loans assigned loans
    @GetMapping("/assigned/{loanStatus}/{assignedStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatusAndAssignedStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignedStatus") String assignedStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignedStatusAndBranchNameOrderByCreatedAtDesc(loanStatus,assignedStatus, branchName), HttpStatus.OK);
    }

    // show BM all loans signed by BOCO
    @GetMapping("/bocoSignature/{bocoSignature}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBocoSignatureAndBranchName(@PathVariable("bocoSignature") String bocoSignature, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBocoSignatureAndBranchNameOrderByCreatedAtDesc(bocoSignature, branchName), HttpStatus.OK);
    }

    // Completely done loan applications
    @GetMapping("/bocoSignature/{bocoSignature}/{completelyDone}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBocoSignatureDoneStatusAndBranchName(@PathVariable("bocoSignature") String bocoSignature, @PathVariable("completelyDone") String completelyDone, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBocoSignatureAndCompletelyDoneAndBranchNameOrderByCreatedAtDesc(bocoSignature, completelyDone, branchName), HttpStatus.OK);
    }

    // show CA all loans signed by BM
    @GetMapping("/bmSignature/{bmSignature}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBMSignatureAndBranchName(@PathVariable("bmSignature") String bmSignature, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBmSignatureAndBranchNameOrderByCreatedAtDesc(bmSignature, branchName), HttpStatus.OK);
    }

    // show CM all loans signed by CA
    @GetMapping("/caSignature/{caSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByCASignatureAndBranchName(@PathVariable("caSignature") String caSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByCaSignatureOrderByCreatedAtDesc(caSignature), HttpStatus.OK);
    }
    // show CA all loans signed by BM
    @GetMapping("/cmSignature/{cmSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByCMSignatureAndBranchName(@PathVariable("cmSignature") String cmSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByCmSignatureOrderByCreatedAtDesc(cmSignature), HttpStatus.OK);
    }
    // show signed tickets for Fin
    @GetMapping("/finSignature/{finSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByFinSignature(@PathVariable("finSignature") String finSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByFinSignatureOrderByCreatedAtDesc(finSignature), HttpStatus.OK);
    }

    // show signed tickets for Fin
    @GetMapping("/boardSignature/{boardSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByBoardSignature(@PathVariable("boardSignature") String boardSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBoardSignatureOrderByCreatedAtDesc(boardSignature), HttpStatus.OK);
    }

    // show BM all loans that have been assessed
//    @GetMapping("/loanStatusAssessed/{loanStatus}/{branchName}/{assessmentStatus}")
//    public ResponseEntity<List<ClientLoan>> getAssessedClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
//        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchNameAndProcessLoanStatus(loanStatus, branchName, assessmentStatus), HttpStatus.OK);
//    }

    // show BM all loans that have been assessed
    @GetMapping("/loanStatusAssessed/{loanStatus}/{branchName}/{pipelineStatus}")
    public ResponseEntity<List<ClientLoan>> getAssessedClientLoanApplicationsByLoanStatusAndBranchName(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("pipelineStatus") String pipelineStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndBranchNameAndPipelineStatusOrderByCreatedAtDesc(loanStatus, branchName, pipelineStatus), HttpStatus.OK);
    }

    // show all loans awaiting for meeting final decision to Credit commit
    @GetMapping("/loanAwaitingDecision/{loanStatus}/{pipelineStatus}/{creditCommit}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByPipelineStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("creditCommit") String creditCommit) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoanByLoanStatusAndPipelineStatusAndCreditCommitOrderByCreatedAtDesc(loanStatus, pipelineStatus, creditCommit), HttpStatus.OK);
    }

    @GetMapping("/loanAwaitingDecision/{loanStatus}/{pipelineStatus}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByPipelineStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("pipelineStatus") String pipelineStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoanByLoanStatusAndPipelineStatusOrderByCreatedAtDesc(loanStatus, pipelineStatus), HttpStatus.OK);
    }

    // show all loans awaiting for meeting final decision to branch managers
    @GetMapping("/finalizedLoan/{loanStatus}/{branchName}/{pipelineStatus}/{creditCommit}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByPipelineStatustoBms(@PathVariable("loanStatus") String loanStatus, @PathVariable("branchName") String branchName, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("creditCommit") String creditCommit) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoanByLoanStatusAndBranchNameAndPipelineStatusAndCreditCommitOrderByCreatedAtDesc(loanStatus, branchName, pipelineStatus, creditCommit), HttpStatus.OK);
    }

    // show BOCO all tickets not signed yet.
    @GetMapping("/ticketNotSigned/{loanStatus}/{processLoanStatus}/{bocoSignature}/{pipelineStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsBySignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("bocoSignature") String bocoSignature, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndBocoSignatureAndPipelineStatusAndBranchNameOrderByCreatedAtDesc(loanStatus, processLoanStatus, bocoSignature, pipelineStatus, branchName), HttpStatus.OK);
    }

    // audit all tickets not signed yet.
    @GetMapping("/auditTicketNotSigned/{loanStatus}/{processLoanStatus}/{finSignature}/{pipelineStatus}")
    public ResponseEntity<List<ClientLoan>> getAuditClientLoanApplicationsBySignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("finSignature") String finSignature, @PathVariable("pipelineStatus") String pipelineStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignatureAndPipelineStatus(loanStatus, processLoanStatus, finSignature, pipelineStatus), HttpStatus.OK);
    }

    // show BM all tickets not signed yet.
    @GetMapping("/bmTicketNotSigned/{loanStatus}/{processLoanStatus}/{bmSignature}/{bocoSignature}/{pipelineStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforBmSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("bmSignature") String bmSignature, @PathVariable("bocoSignature") String bocoSignature, @PathVariable("pipelineStatus") String pipelineStatus, @PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndBocoSignatureAndPipelineStatusAndBranchNameOrderByCreatedAtDesc(loanStatus, processLoanStatus, bmSignature, bocoSignature, pipelineStatus, branchName), HttpStatus.OK);
    }

    // show CA all tickets not signed yet.
    @GetMapping("/caTicketNotSigned/{loanStatus}/{processLoanStatus}/{bmSignature}/{caSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforCaSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("bmSignature") String bmSignature, @PathVariable("caSignature") String caSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndBmSignatureAndCaSignatureOrderByCreatedAtDesc(loanStatus, processLoanStatus,bmSignature, caSignature), HttpStatus.OK);
    }

    // show CM all tickets not signed yet.
    @GetMapping("/cmTicketNotSigned/{loanStatus}/{processLoanStatus}/{caSignature}/{cmSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforCmSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("caSignature") String caSignature, @PathVariable("cmSignature") String cmSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndCaSignatureAndCmSignatureOrderByCreatedAtDesc(loanStatus, processLoanStatus, caSignature, cmSignature), HttpStatus.OK);
    }
    // show Fin all tickets not signed yet.
    @GetMapping("/finTicketNotSigned/{loanStatus}/{processLoanStatus}/{cmSignature}/{finSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforFinSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("cmSignature") String cmSignature, @PathVariable("finSignature") String finSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndCmSignatureAndFinSignatureOrderByCreatedAtDesc(loanStatus, processLoanStatus,cmSignature, finSignature), HttpStatus.OK);
    }

    // show Board all tickets not signed yet.
    @GetMapping("/boardTicketNotSigned/{loanStatus}/{processLoanStatus}/{finSignature}/{boardSignature}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByforBoardSignatureStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("processLoanStatus") String processLoanStatus, @PathVariable("finSignature") String finSignature, @PathVariable("boardSignature") String boardSignature) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndProcessLoanStatusAndFinSignatureAndBoardSignatureOrderByCreatedAtDesc(loanStatus, processLoanStatus, finSignature, boardSignature), HttpStatus.OK);
    }

    // Show loans assigned to a specific loan officer (not yet assessed)
    @GetMapping("/assessmentNotCompleted/{loanStatus}/{assignTo}/{branchName}/{assessmentStatus}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusOrderByCreatedAtDesc(loanStatus, assignTo, branchName, assessmentStatus), HttpStatus.OK);
    }

    // Show loans assigned to a specific loan officer that are assessed
    @GetMapping("/assessmentCompleted/{loanStatus}/{assignTo}/{branchName}/{assessmentStatus}")
    public ResponseEntity<List<ClientLoan>> getProcessedClientLoanApplicationsByLoanStatus(@PathVariable("loanStatus") String loanStatus, @PathVariable("assignTo") String assignTo, @PathVariable("branchName") String branchName, @PathVariable("assessmentStatus") String assessmentStatus) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByLoanStatusAndAssignToAndBranchNameAndProcessLoanStatusOrderByCreatedAtDesc(loanStatus, assignTo, branchName, assessmentStatus), HttpStatus.OK);
    }

    //build delete client loan application REST api
    @DeleteMapping("deleteloan/{id}")
    public ResponseEntity<String> deleteClientLoan(@PathVariable("id") String id) {
        //delete client loan from DB
        clientLoanApplication.deleteClientLoan(id);
        return new ResponseEntity<String>("Application successfully deleted.", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateLoanStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        if (clientLoan.getLoanStatus() != null && !clientLoan.getLoanStatus().isEmpty()) {
            updatedLoanStatus.setLoanStatus(clientLoan.getLoanStatus());
        }
        if (clientLoan.getComment() != null && !clientLoan.getComment().isEmpty()) {
            updatedLoanStatus.setComment(clientLoan.getComment());
        }
        if (clientLoan.getLoanStatusAssigner() != null && !clientLoan.getLoanStatusAssigner().isEmpty()) {
            updatedLoanStatus.setLoanStatusAssigner(clientLoan.getLoanStatusAssigner());
        }
        if (clientLoan.getBocoDate() != null) {
            updatedLoanStatus.setBocoDate(clientLoan.getBocoDate());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isEmpty()) {
            updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/bocoUpdate/{id}")
    public ResponseEntity<String> updateClientLoan(@PathVariable String id, @RequestBody ClientLoan updatedClientLoan) {
        ClientLoan existingClientLoan = clientLoanApplication.getClientLoanApplicationById(id);

        if (updatedClientLoan.getMiddleName() != null && !updatedClientLoan.getMiddleName().isEmpty()) {
            existingClientLoan.setMiddleName(updatedClientLoan.getMiddleName());
        }
        if (updatedClientLoan.getLastName() != null && !updatedClientLoan.getLastName().isEmpty()) {
            existingClientLoan.setLastName(updatedClientLoan.getLastName());
        }
        if (updatedClientLoan.getIdNumber() != null && !updatedClientLoan.getIdNumber().isEmpty()) {
            existingClientLoan.setIdNumber(updatedClientLoan.getIdNumber());
        }
        if (updatedClientLoan.getBranchName() != null && !updatedClientLoan.getBranchName().isEmpty()) {
            existingClientLoan.setBranchName(updatedClientLoan.getBranchName());
        }
        if (updatedClientLoan.getMaritalStatus() != null && !updatedClientLoan.getMaritalStatus().isEmpty()) {
            existingClientLoan.setMaritalStatus(updatedClientLoan.getMaritalStatus());
        }
        if (updatedClientLoan.getGender() != null && !updatedClientLoan.getGender().isEmpty()) {
            existingClientLoan.setGender(updatedClientLoan.getGender());
        }
        if (updatedClientLoan.getDateOfBirth() != null) {
            existingClientLoan.setDateOfBirth(updatedClientLoan.getDateOfBirth());
        }
        if (updatedClientLoan.getPhoneNumber() != null && !updatedClientLoan.getPhoneNumber().isEmpty()) {
            existingClientLoan.setPhoneNumber(updatedClientLoan.getPhoneNumber());
        }
        if (updatedClientLoan.getPlaceOfBusiness() != null && !updatedClientLoan.getPlaceOfBusiness().isEmpty()) {
            existingClientLoan.setPlaceOfBusiness(updatedClientLoan.getPlaceOfBusiness());
        }
        if (updatedClientLoan.getIndustryCode() != null && !updatedClientLoan.getIndustryCode().isEmpty()) {
            existingClientLoan.setIndustryCode(updatedClientLoan.getIndustryCode());
        }
        if (updatedClientLoan.getLoanAmount() != null) {
            existingClientLoan.setLoanAmount(updatedClientLoan.getLoanAmount());
        }
        if (updatedClientLoan.getStreetNo() != null && !updatedClientLoan.getStreetNo().isEmpty()) {
            existingClientLoan.setStreetNo(updatedClientLoan.getStreetNo());
        }
        if (updatedClientLoan.getBusinessName() != null && !updatedClientLoan.getBusinessName().isEmpty()) {
            existingClientLoan.setBusinessName(updatedClientLoan.getBusinessName());
        }
        if (updatedClientLoan.getBusinessStartDate() != null) {
            existingClientLoan.setBusinessStartDate(updatedClientLoan.getBusinessStartDate());
        }
        if (updatedClientLoan.getStreetName() != null && !updatedClientLoan.getStreetName().isEmpty()) {
            existingClientLoan.setStreetName(updatedClientLoan.getStreetName());
        }
        if (updatedClientLoan.getSuburb() != null && !updatedClientLoan.getSuburb().isEmpty()) {
            existingClientLoan.setSuburb(updatedClientLoan.getSuburb());
        }
        if (updatedClientLoan.getCity() != null && !updatedClientLoan.getCity().isEmpty()) {
            existingClientLoan.setCity(updatedClientLoan.getCity());
        }
        if (updatedClientLoan.getTenure() != null) {
            existingClientLoan.setTenure(updatedClientLoan.getTenure());
        }
        if (updatedClientLoan.getNextOfKinName() != null && !updatedClientLoan.getNextOfKinName().isEmpty()) {
            existingClientLoan.setNextOfKinName(updatedClientLoan.getNextOfKinName());
        }
        if (updatedClientLoan.getNextOfKinPhone() != null && !updatedClientLoan.getNextOfKinPhone().isEmpty()) {
            existingClientLoan.setNextOfKinPhone(updatedClientLoan.getNextOfKinPhone());
        }
        if (updatedClientLoan.getNextOfKinRelationship() != null && !updatedClientLoan.getNextOfKinRelationship().isEmpty()) {
            existingClientLoan.setNextOfKinRelationship(updatedClientLoan.getNextOfKinRelationship());
        }
        if (updatedClientLoan.getNextOfKinAddress() != null && !updatedClientLoan.getNextOfKinAddress().isEmpty()) {
            existingClientLoan.setNextOfKinAddress(updatedClientLoan.getNextOfKinAddress());
        }
        if (updatedClientLoan.getNextOfKinName2() != null && !updatedClientLoan.getNextOfKinName2().isEmpty()) {
            existingClientLoan.setNextOfKinName2(updatedClientLoan.getNextOfKinName2());
        }
        if (updatedClientLoan.getNextOfKinPhone2() != null && !updatedClientLoan.getNextOfKinPhone2().isEmpty()) {
            existingClientLoan.setNextOfKinPhone2(updatedClientLoan.getNextOfKinPhone2());
        }
        if (updatedClientLoan.getNextOfKinRelationship2() != null && !updatedClientLoan.getNextOfKinRelationship2().isEmpty()) {
            existingClientLoan.setNextOfKinRelationship2(updatedClientLoan.getNextOfKinRelationship2());
        }
        if (updatedClientLoan.getNextOfKinAddress2() != null && !updatedClientLoan.getNextOfKinAddress2().isEmpty()) {
            existingClientLoan.setNextOfKinAddress2(updatedClientLoan.getNextOfKinAddress2());
        }

        clientRepository.save(existingClientLoan);

        return new ResponseEntity<String>("Client Loan successfully updated.", HttpStatus.OK);
    }


    // Assign each loan to a loan officer
    @PutMapping("/assignTo/{id}")
    public ResponseEntity<String> updateAssignTo(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedAssignTo = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getAssignTo() != null) updatedAssignTo.setAssignTo(clientLoan.getAssignTo());
        if (clientLoan.getAssignedBy() != null) updatedAssignTo.setAssignedBy(clientLoan.getAssignedBy());
        if (clientLoan.getProcessLoanStatus() != null) updatedAssignTo.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        if (clientLoan.getAdditionalRemarks() != null) updatedAssignTo.setAdditionalRemarks(clientLoan.getAdditionalRemarks());
        updatedAssignTo.setAssignedStatus("Assigned");
        if (clientLoan.getBmDateAssignLo() != null) updatedAssignTo.setBmDateAssignLo(clientLoan.getBmDateAssignLo());
        if (clientLoan.getPipelineStatus() != null) updatedAssignTo.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updatedAssignTo);
        return ResponseEntity.ok("Loan Status successfully updated.");
    }

    // Set/update status indicating that LO has completed processing the application
    @PutMapping("/updateLoanAssessmentStatus/{id}")
    public ResponseEntity<String> assessmentCompleteStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateProcessLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getProcessLoanStatus() != null) updateProcessLoanStatus.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        if (clientLoan.getProcessedBy() != null) updateProcessLoanStatus.setProcessedBy(clientLoan.getProcessedBy());
        if (clientLoan.getLoDate() != null) updateProcessLoanStatus.setLoDate(clientLoan.getLoDate());
        if (clientLoan.getPipelineStatus() != null) updateProcessLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updateProcessLoanStatus);
        return ResponseEntity.ok("Loan Assessment Status successfully updated.");
    }

    // Update predisbursement ticket for BOCO signature
    @PutMapping("/updateTicketSignature/{id}")
    public ResponseEntity<String> ticketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getBocoSignature() != null) updateSignatureStatus.setBocoSignature(clientLoan.getBocoSignature());
        if (clientLoan.getBocoName() != null) updateSignatureStatus.setBocoName(clientLoan.getBocoName());
        clientRepository.save(updateSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed.");
    }

    // Update ticket information
    @PutMapping("/updateTicketInfo/{id}")
    public ResponseEntity<String> updateTicketInfo(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getLessFees() != null) updateSignatureStatus.setLessFees(clientLoan.getLessFees());
        if (clientLoan.getApplicationFee() != null) updateSignatureStatus.setApplicationFee(clientLoan.getApplicationFee());
        if (clientLoan.getMeetingLoanAmount() != null) updateSignatureStatus.setMeetingLoanAmount(clientLoan.getMeetingLoanAmount());
        if (clientLoan.getMeetingCashHandlingFee() != null) updateSignatureStatus.setMeetingCashHandlingFee(clientLoan.getMeetingCashHandlingFee());
        if (clientLoan.getMeetingInterestRate() != null) updateSignatureStatus.setMeetingInterestRate(clientLoan.getMeetingInterestRate());
        if (clientLoan.getMeetingRepaymentAmount() != null) updateSignatureStatus.setMeetingRepaymentAmount(clientLoan.getMeetingRepaymentAmount());
        if (clientLoan.getMeetingTenure() != null) updateSignatureStatus.setMeetingTenure(clientLoan.getMeetingTenure());
        if (clientLoan.getMeetingUpfrontFee() != null) updateSignatureStatus.setMeetingUpfrontFee(clientLoan.getMeetingUpfrontFee());
        clientRepository.save(updateSignatureStatus);
        return ResponseEntity.ok("Ticket successfully updated.");
    }

    // Update predisbursement ticket for BM signature
    @PutMapping("/updateBmSignature/{id}")
    public ResponseEntity<String> bmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateBmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getBmSignature() != null) updateBmSignatureStatus.setBmSignature(clientLoan.getBmSignature());
        if (clientLoan.getBmName() != null) updateBmSignatureStatus.setBmName(clientLoan.getBmName());
        clientRepository.save(updateBmSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed by BM.");
    }

    // Update predisbursement ticket for CM signature
    @PutMapping("/updateCmSignature/{id}")
    public ResponseEntity<String> cmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateCmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getCmSignature() != null) updateCmSignatureStatus.setCmSignature(clientLoan.getCmSignature());
        if (clientLoan.getCmName() != null) updateCmSignatureStatus.setCmName(clientLoan.getCmName());
        clientRepository.save(updateCmSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed by CM.");
    }

    // Update predisbursement ticket for CA signature
    @PutMapping("/updateCaSignature/{id}")
    public ResponseEntity<String> caTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateCaSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getCaSignature() != null) updateCaSignatureStatus.setCaSignature(clientLoan.getCaSignature());
        if (clientLoan.getCaName() != null) updateCaSignatureStatus.setCaName(clientLoan.getCaName());
        clientRepository.save(updateCaSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed by CA.");
    }

    // Update predisbursement ticket for Fin signature
    @PutMapping("/updateFinSignature/{id}")
    public ResponseEntity<String> finTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateFinSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getFinSignature() != null) updateFinSignatureStatus.setFinSignature(clientLoan.getFinSignature());
        if (clientLoan.getFinName() != null) updateFinSignatureStatus.setFinName(clientLoan.getFinName());
        clientRepository.save(updateFinSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed by Fin.");
    }

    // Update predisbursement ticket for Board signature
    @PutMapping("/updateBoardSignature/{id}")
    public ResponseEntity<String> boardTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updateBoardSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        if (clientLoan.getBoardSignature() != null) updateBoardSignatureStatus.setBoardSignature(clientLoan.getBoardSignature());
        if (clientLoan.getBoardName() != null) updateBoardSignatureStatus.setBoardName(clientLoan.getBoardName());
        clientRepository.save(updateBoardSignatureStatus);
        return ResponseEntity.ok("Ticket successfully signed by Board.");
    }

    @GetMapping("/loanFileId/{loanFileId}")
    public ResponseEntity<ClientLoan> getClientLoanId(@PathVariable("loanFileId") String loanFileId) {
        return new ResponseEntity<ClientLoan>(clientRepository.findByLoanFileId(loanFileId), HttpStatus.OK);
    }

    // email to Bocos
    @PostMapping("newClientloanEmail/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendLoanSuccess(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendLoanSuccessMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "New loan Application", emailText);
//        log.info(String.valueOf(clientLoan));
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    @PostMapping("sendBulkEmail")
    public ResponseEntity<ClientLoan> sendBulkEmail(@RequestBody BulkEmail bulkEmail) {
        String recipientEmail = emailSender.sendBulkEmail(bulkEmail.getRecipients(), bulkEmail.getSubject(), bulkEmail.getMessage());
        emailSender.sendBulk(bulkEmail.getRecipients(), bulkEmail.getSubject(), recipientEmail);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(Arrays.toString(bulkEmail.getRecipients()), bulkEmail.getSubject()), HttpStatus.OK);
    }

    @PostMapping("sendEmail")
    public ResponseEntity<ClientLoan> sendEmail(@RequestBody Email email) {
//        String recipientEmail = emailSender.send(email.getRecipient(), email.getSubject(), email.getMessage());
        log.info("email: {}", email);
        emailSender.send(email.getRecipient(), email.getSubject(), email.getMessage());
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(email.getRecipient(), email.getSubject()), HttpStatus.OK);
    }


    //email to Bms
    @PostMapping("bocoCheckLoanStatus/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendBocoCheck(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendBocoCheckMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Checked Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to LOs
    @PostMapping("bmAssignLoanOfficer/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendBmAssignLo(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendBmAssignLoMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //email to schedule meeting with credit commit
    @PostMapping("bmScheduleMeeting/{recipientName}/{recipientEmail}/{recipientSubject}/{recipientMessage}/{senderName}")
    public ResponseEntity<ClientLoan> sendScheduleMeeting(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail, @PathVariable("recipientSubject") String recipientSubject, @PathVariable("recipientMessage") String recipientMessage, @PathVariable("senderName") String senderName) {
        String emailText = emailSender.sendScheduleMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendMeetingScheduleSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
    }

    //email to Clients
    @PostMapping("sendClientConfirmation/{recipientName}/{recipientEmail}")
    public ResponseEntity<ClientLoan> sendClientConfirmation(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail) {
        String emailText = emailSender.sendClientConfirmationMsg(recipientName, "New loan Application", "");
        emailSender.send(recipientEmail, "Assigned Loan Application", emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendLoanSuccess(recipientName, recipientEmail), HttpStatus.OK);
    }

    //get applications by BranchName and loan display loan with status pending for BOCO
    @GetMapping("/byBranch/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationByBranchName(@PathVariable("branchName") String branchName) {
        return new ResponseEntity<List<ClientLoan>>(clientRepository.findClientLoansByBranchNameOrderByCreatedAtDesc(branchName), HttpStatus.OK);
    }

    //display unchecked loans  with status pending for BOCO

    @GetMapping("/unchecked/{loanStatus}/{branchName}")
    public ResponseEntity<List<ClientLoan>> getClientLoanApplicationByBranchNameAndLoanStatus(
            @PathVariable("loanStatus") String loanStatus,
            @PathVariable("branchName") String branchName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size); // Set the limit to 20 records
        List<ClientLoan> clientLoans = clientRepository.findClientLoansByLoanStatusAndBranchNameOrderByCreatedAtDesc(
                loanStatus, branchName, pageable);
        return new ResponseEntity<>(clientLoans, HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateMeeting/{id}")
    public ResponseEntity<String> updateLoanMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanMeeting = clientLoanApplication.getClientLoanApplicationById(id);

        // Update only if the new value is not null or blank
        if (clientLoan.getMeetingLoanAmount() != null) {
            updatedLoanMeeting.setMeetingLoanAmount(clientLoan.getMeetingLoanAmount());
        }
        if (clientLoan.getMeetingTenure() != null) {
            updatedLoanMeeting.setMeetingTenure(clientLoan.getMeetingTenure());
        }
        if (clientLoan.getMeetingInterestRate() != null) {
            updatedLoanMeeting.setMeetingInterestRate(clientLoan.getMeetingInterestRate());
        }
        if (clientLoan.getMeetingOnWhichBasis() != null && !clientLoan.getMeetingOnWhichBasis().isBlank()) {
            updatedLoanMeeting.setMeetingOnWhichBasis(clientLoan.getMeetingOnWhichBasis());
        }
        if (clientLoan.getMeetingCashHandlingFee() != null) {
            updatedLoanMeeting.setMeetingCashHandlingFee(clientLoan.getMeetingCashHandlingFee());
        }
        if (clientLoan.getMeetingRepaymentAmount() != null) {
            updatedLoanMeeting.setMeetingRepaymentAmount(clientLoan.getMeetingRepaymentAmount());
        }
        if (clientLoan.getMeetingProduct() != null && !clientLoan.getMeetingProduct().isBlank()) {
            updatedLoanMeeting.setMeetingProduct(clientLoan.getMeetingProduct());
        }
        if (clientLoan.getMeetingRN() != null && !clientLoan.getMeetingRN().isBlank()) {
            updatedLoanMeeting.setMeetingRN(clientLoan.getMeetingRN());
        }
        if (clientLoan.getMeetingUpfrontFee() != null) {
            updatedLoanMeeting.setMeetingUpfrontFee(clientLoan.getMeetingUpfrontFee());
        }
        if (clientLoan.getMeetingFinalizedBy() != null && !clientLoan.getMeetingFinalizedBy().isBlank()) {
            updatedLoanMeeting.setMeetingFinalizedBy(clientLoan.getMeetingFinalizedBy());
        }
        if (clientLoan.getCcDate() != null) {
            updatedLoanMeeting.setCcDate(clientLoan.getCcDate());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isBlank()) {
            updatedLoanMeeting.setPipelineStatus(clientLoan.getPipelineStatus());
        }

        clientRepository.save(updatedLoanMeeting);
        return new ResponseEntity<>("Loan Meeting successfully updated.", HttpStatus.OK);
    }

    // Update meeting columns
    @PutMapping("/updateBmDateMeeting/{id}")
    public ResponseEntity<String> updateBmDateMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        // Update fields only if the new values are not null or blank
        if (clientLoan.getBmDateMeeting() != null) {
            updatedLoanStatus.setBmDateMeeting(clientLoan.getBmDateMeeting());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isBlank()) {
            updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        }
        if (clientLoan.getBmSetMeeting() != null) {
            updatedLoanStatus.setBmSetMeeting(clientLoan.getBmSetMeeting());
        }
        if (clientLoan.getCreditCommit() != null) {
            updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<>("Meeting status successfully updated.", HttpStatus.OK);
    }

    // Update meeting columns
    @PutMapping("/updateCcFinalMeeting/{id}")
    public ResponseEntity<String> updateCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        // Update fields only if the new values are not null or blank
        if (clientLoan.getCcDate() != null) {
            updatedLoanStatus.setCcDate(clientLoan.getCcDate());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isBlank()) {
            updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        }
        if (clientLoan.getCreditCommit() != null) {
            updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<>("Meeting status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/updateRecommentCcFinalMeeting/{id}")
    public ResponseEntity<String> updateRecommentCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        // Update fields only if the new values are not null or blank
        if (clientLoan.getCcDate() != null) {
            updatedLoanStatus.setCcDate(clientLoan.getCcDate());
        }
        if (clientLoan.getCreditCommit() != null) {
            updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isBlank()) {
            updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<>("Meeting status successfully updated.", HttpStatus.OK);
    }

    // Update meeting columns
    @PutMapping("/predisbursementTicket/{id}")
    public ResponseEntity<String> updatePredisbursementTicket(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        // Update fields only if the new values are not null or blank
        if (clientLoan.getPredisDate() != null) {
            updatedLoanStatus.setPredisDate(clientLoan.getPredisDate());
        }
        if (clientLoan.getPipelineStatus() != null && !clientLoan.getPipelineStatus().isBlank()) {
            updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<>("Predisbursement ticket successfully updated.", HttpStatus.OK);
    }

    // Update completely done disbursed tickets
    @PutMapping("/complete/{id}")
    public ResponseEntity<String> complete(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);

        // Update field only if the new value is not null
        if (clientLoan.getCompletelyDone() != null) {
            updatedLoanStatus.setCompletelyDone(clientLoan.getCompletelyDone());
        }

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<>("Done.", HttpStatus.OK);
    }

    // Email to schedule meeting with credit commit
    @PostMapping("sendFinalMeetingMsg/{recipientName}/{recipientEmail}/{recipientSubject}/{recipientMessage}/{senderName}")
    public ResponseEntity<ClientLoan> sendFinalMeeting(
            @PathVariable("recipientName") String recipientName,
            @PathVariable("recipientEmail") String recipientEmail,
            @PathVariable("recipientSubject") String recipientSubject,
            @PathVariable("recipientMessage") String recipientMessage,
            @PathVariable("senderName") String senderName
    ) {
        // Construct email text
        String emailText = emailSender.sendFinalMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);

        // Send the email
        emailSender.send(recipientEmail, recipientSubject, emailText);

        // Return success response
        return new ResponseEntity<>(
                clientLoanApplication.sendFinalMeetingSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName),
                HttpStatus.OK
        );
    }

    //Update Loan Info columns
    @PutMapping("/updateLoan/{id}")
    public ResponseEntity<String> updateLoan(@PathVariable String id, @RequestBody ClientLoan clientLoan) {
        ClientLoan updatedLoan = clientLoanApplication.getClientLoanApplicationById(id);

        // Update only if the new value is not null or blank
        if (clientLoan.getIdNumber() != null && !clientLoan.getIdNumber().isBlank()) {
            updatedLoan.setIdNumber(clientLoan.getIdNumber());
        }
        if (clientLoan.getMaritalStatus() != null && !clientLoan.getMaritalStatus().isBlank()) {
            updatedLoan.setMaritalStatus(clientLoan.getMaritalStatus());
        }
        if (clientLoan.getGender() != null && !clientLoan.getGender().isBlank()) {
            updatedLoan.setGender(clientLoan.getGender());
        }
        if (clientLoan.getDateOfBirth() != null) {
            updatedLoan.setDateOfBirth(clientLoan.getDateOfBirth());
        }
        if (clientLoan.getPhoneNumber() != null && !clientLoan.getPhoneNumber().isBlank()) {
            updatedLoan.setPhoneNumber(clientLoan.getPhoneNumber());
        }
        if (clientLoan.getPlaceOfBusiness() != null && !clientLoan.getPlaceOfBusiness().isBlank()) {
            updatedLoan.setPlaceOfBusiness(clientLoan.getPlaceOfBusiness());
        }
        if (clientLoan.getIndustryCode() != null && !clientLoan.getIndustryCode().isBlank()) {
            updatedLoan.setIndustryCode(clientLoan.getIndustryCode());
        }
        if (clientLoan.getStreetNo() != null && !clientLoan.getStreetNo().isBlank()) {
            updatedLoan.setStreetNo(clientLoan.getStreetNo());
        }
        if (clientLoan.getStreetName() != null && !clientLoan.getStreetName().isBlank()) {
            updatedLoan.setStreetName(clientLoan.getStreetName());
        }
        if (clientLoan.getSuburb() != null && !clientLoan.getSuburb().isBlank()) {
            updatedLoan.setSuburb(clientLoan.getSuburb());
        }
        if (clientLoan.getCity() != null && !clientLoan.getCity().isBlank()) {
            updatedLoan.setCity(clientLoan.getCity());
        }
        if (clientLoan.getLoanAmount() != null) {
            updatedLoan.setLoanAmount(clientLoan.getLoanAmount());
        }
        if (clientLoan.getTenure() != null) {
            updatedLoan.setTenure(clientLoan.getTenure());
        }
        if (clientLoan.getBusinessName() != null && !clientLoan.getBusinessName().isBlank()) {
            updatedLoan.setBusinessName(clientLoan.getBusinessName());
        }
        if (clientLoan.getBusinessStartDate() != null) {
            updatedLoan.setBusinessStartDate(clientLoan.getBusinessStartDate());
        }
        if (clientLoan.getBranchName() != null && !clientLoan.getBranchName().isBlank()) {
            updatedLoan.setBranchName(clientLoan.getBranchName());
        }
        if (clientLoan.getNextOfKinName() != null && !clientLoan.getNextOfKinName().isBlank()) {
            updatedLoan.setNextOfKinName(clientLoan.getNextOfKinName());
        }
        if (clientLoan.getNextOfKinPhone() != null && !clientLoan.getNextOfKinPhone().isBlank()) {
            updatedLoan.setNextOfKinPhone(clientLoan.getNextOfKinPhone());
        }
        if (clientLoan.getNextOfKinRelationship() != null && !clientLoan.getNextOfKinRelationship().isBlank()) {
            updatedLoan.setNextOfKinRelationship(clientLoan.getNextOfKinRelationship());
        }
        if (clientLoan.getNextOfKinAddress() != null && !clientLoan.getNextOfKinAddress().isBlank()) {
            updatedLoan.setNextOfKinAddress(clientLoan.getNextOfKinAddress());
        }
        if (clientLoan.getNextOfKinName2() != null && !clientLoan.getNextOfKinName2().isBlank()) {
            updatedLoan.setNextOfKinName2(clientLoan.getNextOfKinName2());
        }
        if (clientLoan.getNextOfKinPhone2() != null && !clientLoan.getNextOfKinPhone2().isBlank()) {
            updatedLoan.setNextOfKinPhone2(clientLoan.getNextOfKinPhone2());
        }
        if (clientLoan.getNextOfKinRelationship2() != null && !clientLoan.getNextOfKinRelationship2().isBlank()) {
            updatedLoan.setNextOfKinRelationship2(clientLoan.getNextOfKinRelationship2());
        }
        if (clientLoan.getNextOfKinAddress2() != null && !clientLoan.getNextOfKinAddress2().isBlank()) {
            updatedLoan.setNextOfKinAddress2(clientLoan.getNextOfKinAddress2());
        }

        clientRepository.save(updatedLoan);
        return new ResponseEntity<>("Loan successfully updated.", HttpStatus.OK);
    }


}




