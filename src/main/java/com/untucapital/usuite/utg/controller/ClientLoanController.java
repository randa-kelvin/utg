package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.BulkEmail;
import com.google.gson.Gson;
import com.untucapital.usuite.utg.dto.Email;
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


    //build save loan REST API
//    @PostMapping
//    @Operation(summary = "Create a new client loan application")
//    public ResponseEntity<ClientLoan> saveClientLoan(@RequestBody ClientLoan clientLoan) throws ParseException {
//        log.info(String.valueOf(clientLoan));
//        return new ResponseEntity<ClientLoan>(clientLoanApplication.saveClientLoan(clientLoan), HttpStatus.CREATED);
//    }

    @PostMapping
    @Operation(summary = "Create a new client loan application")
    public ResponseEntity<ClientLoan> saveClientLoan(@RequestBody ClientLoan clientLoan) throws ParseException {
        log.info(String.valueOf(clientLoan));
        // Process the client loan to set default values
        clientLoan = clientLoanService.processClientLoan(clientLoan);
        return new ResponseEntity<>(clientLoanApplication.saveClientLoan(clientLoan), HttpStatus.CREATED);
    }



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
        updatedLoanStatus.setLoanStatus(clientLoan.getLoanStatus());
        updatedLoanStatus.setComment(clientLoan.getComment());
        updatedLoanStatus.setLoanStatusAssigner(clientLoan.getLoanStatusAssigner());
        updatedLoanStatus.setBocoDate(clientLoan.getBocoDate());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());

        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/bocoUpdate/{id}")
    public ResponseEntity<String> updateClientLoan(@PathVariable String id, @RequestBody ClientLoan updatedClientLoan) {
        ClientLoan existingClientLoan = clientLoanApplication.getClientLoanApplicationById(id);

        // Update the fields with values from the updatedClientLoan
        existingClientLoan.setMiddleName(updatedClientLoan.getMiddleName());
        existingClientLoan.setLastName(updatedClientLoan.getLastName());
        existingClientLoan.setIdNumber(updatedClientLoan.getIdNumber());
        existingClientLoan.setBranchName(updatedClientLoan.getBranchName());
        existingClientLoan.setMaritalStatus(updatedClientLoan.getMaritalStatus());
        existingClientLoan.setGender(updatedClientLoan.getGender());
        existingClientLoan.setDateOfBirth(updatedClientLoan.getDateOfBirth());
        existingClientLoan.setPhoneNumber(updatedClientLoan.getPhoneNumber());
        existingClientLoan.setPlaceOfBusiness(updatedClientLoan.getPlaceOfBusiness());
        existingClientLoan.setIndustryCode(updatedClientLoan.getIndustryCode());
        existingClientLoan.setLoanAmount(updatedClientLoan.getLoanAmount());
        existingClientLoan.setStreetNo(updatedClientLoan.getStreetNo());
        existingClientLoan.setBusinessName(updatedClientLoan.getBusinessName());
        existingClientLoan.setBusinessStartDate(updatedClientLoan.getBusinessStartDate());
        existingClientLoan.setStreetName(updatedClientLoan.getStreetName());
        existingClientLoan.setSuburb(updatedClientLoan.getSuburb());
        existingClientLoan.setCity(updatedClientLoan.getCity());
        existingClientLoan.setTenure(updatedClientLoan.getTenure());
        existingClientLoan.setNextOfKinName(updatedClientLoan.getNextOfKinName());
        existingClientLoan.setNextOfKinPhone(updatedClientLoan.getNextOfKinPhone());
        existingClientLoan.setNextOfKinRelationship(updatedClientLoan.getNextOfKinRelationship());
        existingClientLoan.setNextOfKinAddress(updatedClientLoan.getNextOfKinAddress());
        existingClientLoan.setNextOfKinName2(updatedClientLoan.getNextOfKinName2());
        existingClientLoan.setNextOfKinPhone2(updatedClientLoan.getNextOfKinPhone2());
        existingClientLoan.setNextOfKinRelationship2(updatedClientLoan.getNextOfKinRelationship2());
        existingClientLoan.setNextOfKinAddress2(updatedClientLoan.getNextOfKinAddress2());

        clientRepository.save(existingClientLoan);

        return new ResponseEntity<String>("Client Loan successfully updated.", HttpStatus.OK);
    }


    // assign each loan to a loan officer
    @PutMapping("/assignTo/{id}")
    public ResponseEntity<String> updateAssignTo(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedAssignTo = clientLoanApplication.getClientLoanApplicationById(id);
        updatedAssignTo.setAssignTo(clientLoan.getAssignTo());
        updatedAssignTo.setAssignedBy(clientLoan.getAssignedBy());
        updatedAssignTo.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        updatedAssignTo.setAdditionalRemarks(clientLoan.getAdditionalRemarks());
        updatedAssignTo.setAssignedStatus("Assigned");
        updatedAssignTo.setBmDateAssignLo(clientLoan.getBmDateAssignLo());
        updatedAssignTo.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updatedAssignTo);
        return new ResponseEntity<String>("Loan Status successfully updated.", HttpStatus.OK);
    }

    // set/Update status that LO has completed processing application
    @PutMapping("/updateLoanAssessmentStatus/{id}")
    public ResponseEntity<String> assessmentCompleteStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateProcessLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateProcessLoanStatus.setProcessLoanStatus(clientLoan.getProcessLoanStatus());
        updateProcessLoanStatus.setProcessedBy(clientLoan.getProcessedBy());
        updateProcessLoanStatus.setLoDate(clientLoan.getLoDate());
        updateProcessLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updateProcessLoanStatus);
        return new ResponseEntity<String>("Loan Assessment Status successfully updated.", HttpStatus.OK);
    }

    // update predisbursement ticket for BOCO Signature
    @PutMapping("/updateTicketSignature/{id}")
    public ResponseEntity<String> ticketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateSignatureStatus.setBocoSignature(clientLoan.getBocoSignature());
        updateSignatureStatus.setBocoName(clientLoan.getBocoName());
//        updateSignatureStatus.setBocoSignatureImage(clientLoan.getBocoSignatureImage());
//        updateSignatureStatus.setLessFees(clientLoan.getLessFees());
//        updateSignatureStatus.setApplicationFee(clientLoan.getApplicationFee());
        clientRepository.save(updateSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    @PutMapping("/updateTicketInfo/{id}")
    public ResponseEntity<String> updateTicketInfo(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateSignatureStatus.setLessFees(clientLoan.getLessFees());
        updateSignatureStatus.setApplicationFee(clientLoan.getApplicationFee());
        updateSignatureStatus.setMeetingLoanAmount(clientLoan.getMeetingLoanAmount());
        updateSignatureStatus.setMeetingCashHandlingFee(clientLoan.getMeetingCashHandlingFee());
        updateSignatureStatus.setMeetingInterestRate(clientLoan.getMeetingInterestRate());
        updateSignatureStatus.setMeetingRepaymentAmount(clientLoan.getMeetingRepaymentAmount());
        updateSignatureStatus.setMeetingTenure(clientLoan.getMeetingTenure());
        updateSignatureStatus.setMeetingUpfrontFee(clientLoan.getMeetingUpfrontFee());

        clientRepository.save(updateSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    // update predisbursement ticket for bm Signature
    @PutMapping("/updateBmSignature/{id}")
    public ResponseEntity<String> bmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateBmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateBmSignatureStatus.setBmSignature(clientLoan.getBmSignature());
        updateBmSignatureStatus.setBmName(clientLoan.getBmName());
        clientRepository.save(updateBmSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }
    // update predisbursement ticket for CM Signature
    @PutMapping("/updateCmSignature/{id}")
    public ResponseEntity<String> cmTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateCmSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateCmSignatureStatus.setCmSignature(clientLoan.getCmSignature());
        updateCmSignatureStatus.setCmName(clientLoan.getCmName());
        clientRepository.save(updateCmSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    // update predisbursement ticket for CA Signature
    @PutMapping("/updateCaSignature/{id}")
    public ResponseEntity<String> caTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateCaSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateCaSignatureStatus.setCaSignature(clientLoan.getCaSignature());
        updateCaSignatureStatus.setCaName(clientLoan.getCaName());
        clientRepository.save(updateCaSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }
    // update predisbursement ticket for Fin Signature
    @PutMapping("/updateFinSignature/{id}")
    public ResponseEntity<String> finTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateFinSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateFinSignatureStatus.setFinSignature(clientLoan.getFinSignature());
        updateFinSignatureStatus.setFinName(clientLoan.getFinName());
        clientRepository.save(updateFinSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
    }

    // update predisbursement ticket for Board Signature
    @PutMapping("/updateBoardSignature/{id}")
    public ResponseEntity<String> boardTicketStatus(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updateBoardSignatureStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updateBoardSignatureStatus.setBoardSignature(clientLoan.getBoardSignature());
        updateBoardSignatureStatus.setBoardName(clientLoan.getBoardName());
        clientRepository.save(updateBoardSignatureStatus);
        return new ResponseEntity<String>("Ticket successfully signed.", HttpStatus.OK);
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
//    @PostMapping("bmScheduleMeeting")
//    public ResponseEntity<ClientLoan> sendScheduleMeeting(@RequestParam("recipientName") String recipientName, @RequestParam("recipientEmail") String recipientEmail, @RequestParam("recipientSubject") String recipientSubject, @RequestParam("recipientMessage") String recipientMessage, @RequestParam("senderName") String senderName) {
//        String emailText = emailSender.sendScheduleMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
//        emailSender.send(recipientEmail, recipientSubject, emailText);
//        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendMeetingScheduleSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
//    }

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
    public ResponseEntity<String> updateLoanMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanMeeting = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanMeeting.setMeetingLoanAmount(clientLoan.getMeetingLoanAmount());
        updatedLoanMeeting.setMeetingTenure(clientLoan.getMeetingTenure());
        updatedLoanMeeting.setMeetingInterestRate(clientLoan.getMeetingInterestRate());
        updatedLoanMeeting.setMeetingOnWhichBasis(clientLoan.getMeetingOnWhichBasis());
        updatedLoanMeeting.setMeetingCashHandlingFee(clientLoan.getMeetingCashHandlingFee());
        updatedLoanMeeting.setMeetingRepaymentAmount(clientLoan.getMeetingRepaymentAmount());
        updatedLoanMeeting.setMeetingProduct(clientLoan.getMeetingProduct());
        updatedLoanMeeting.setMeetingRN(clientLoan.getMeetingRN());
        updatedLoanMeeting.setMeetingUpfrontFee(clientLoan.getMeetingUpfrontFee());
        updatedLoanMeeting.setMeetingFinalizedBy(clientLoan.getMeetingFinalizedBy());
        updatedLoanMeeting.setCcDate(clientLoan.getCcDate());
        updatedLoanMeeting.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updatedLoanMeeting);
        return new ResponseEntity<String>("Loan Meeting successfully updated.", HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateBmDateMeeting/{id}")
    public ResponseEntity<String> updateBmDateMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setBmDateMeeting(clientLoan.getBmDateMeeting());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        updatedLoanStatus.setBmSetMeeting(clientLoan.getBmSetMeeting());
        updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/updateCcFinalMeeting/{id}")
    public ResponseEntity<String> updateCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setCcDate(clientLoan.getCcDate());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
    }

    @PutMapping("/updateRecommentCcFinalMeeting/{id}")
    public ResponseEntity<String> updateRecommentCcFinalMeeting(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setCcDate(clientLoan.getCcDate());
        updatedLoanStatus.setCreditCommit(clientLoan.getCreditCommit());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Meeting status successfully updated.", HttpStatus.OK);
    }

    //Update meeting columns
    @PutMapping("/predisbursementTicket/{id}")
    public ResponseEntity<String> updatePredisbursementTicket(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setPredisDate(clientLoan.getPredisDate());
        updatedLoanStatus.setPipelineStatus(clientLoan.getPipelineStatus());
        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Predisbursement ticket successfully updated.", HttpStatus.OK);
    }

    //Update completely done disbursed tickets
    @PutMapping("/complete/{id}")
    public ResponseEntity<String> complete(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoanStatus = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoanStatus.setCompletelyDone(clientLoan.getCompletelyDone());
        clientRepository.save(updatedLoanStatus);
        return new ResponseEntity<String>("Done.", HttpStatus.OK);
    }

    //email to schedule meeting with credit commit
    @PostMapping("sendFinalMeetingMsg/{recipientName}/{recipientEmail}/{recipientSubject}/{recipientMessage}/{senderName}")
    public ResponseEntity<ClientLoan> sendFinalMeeting(@PathVariable("recipientName") String recipientName, @PathVariable("recipientEmail") String recipientEmail, @PathVariable("recipientSubject") String recipientSubject, @PathVariable("recipientMessage") String recipientMessage, @PathVariable("senderName") String senderName) {
        String emailText = emailSender.sendFinalMeetingMsg(recipientName, recipientSubject, recipientMessage, senderName);
        emailSender.send(recipientEmail, recipientSubject, emailText);
        return new ResponseEntity<ClientLoan>(clientLoanApplication.sendFinalMeetingSuccess(recipientName, recipientEmail, recipientSubject, recipientMessage, senderName), HttpStatus.OK);
    }

    //Update Loan Info columns
    @PutMapping("/updateLoan/{id}")
    public ResponseEntity<String> updateLoan(@PathVariable String id, @RequestBody ClientLoan clientLoan){
        ClientLoan updatedLoan = clientLoanApplication.getClientLoanApplicationById(id);
        updatedLoan.setIdNumber(clientLoan.getIdNumber());
        updatedLoan.setMaritalStatus(clientLoan.getMaritalStatus());
        updatedLoan.setGender(clientLoan.getGender());
        updatedLoan.setDateOfBirth(clientLoan.getDateOfBirth());
        updatedLoan.setPhoneNumber(clientLoan.getPhoneNumber());
        updatedLoan.setPlaceOfBusiness(clientLoan.getPlaceOfBusiness());
        updatedLoan.setIndustryCode(clientLoan.getIndustryCode());
        updatedLoan.setStreetNo(clientLoan.getStreetNo());
        updatedLoan.setStreetName(clientLoan.getStreetName());
        updatedLoan.setSuburb(clientLoan.getSuburb());
        updatedLoan.setCity(clientLoan.getCity());
        updatedLoan.setLoanAmount(clientLoan.getLoanAmount());
        updatedLoan.setTenure(clientLoan.getTenure());
        updatedLoan.setBusinessName(clientLoan.getBusinessName());
        updatedLoan.setPlaceOfBusiness(clientLoan.getPlaceOfBusiness());
        updatedLoan.setBusinessStartDate(clientLoan.getBusinessStartDate());
        updatedLoan.setBranchName(clientLoan.getBranchName());
        updatedLoan.setNextOfKinName(clientLoan.getNextOfKinName());
        updatedLoan.setNextOfKinPhone(clientLoan.getNextOfKinPhone());
        updatedLoan.setNextOfKinRelationship(clientLoan.getNextOfKinRelationship());
        updatedLoan.setNextOfKinAddress(clientLoan.getNextOfKinAddress());
        updatedLoan.setNextOfKinName2(clientLoan.getNextOfKinName2());
        updatedLoan.setNextOfKinPhone2(clientLoan.getNextOfKinPhone2());
        updatedLoan.setNextOfKinRelationship2(clientLoan.getNextOfKinRelationship2());
        updatedLoan.setNextOfKinAddress2(clientLoan.getNextOfKinAddress2());

        clientRepository.save(updatedLoan);
        return new ResponseEntity<String>("Loan successfully updated.", HttpStatus.OK);
    }

}




