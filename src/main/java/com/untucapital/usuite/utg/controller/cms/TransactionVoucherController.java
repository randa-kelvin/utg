package com.untucapital.usuite.utg.controller.cms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.dto.cms.ApproverRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherInitiatorRequest;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherResponse;
import com.untucapital.usuite.utg.dto.cms.TransactionVoucherUpdateRequest;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.service.cms.TransactionVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author tjchidanika
 * @created 4/10/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("cms/transaction-voucher")
public class TransactionVoucherController {
    private final TransactionVoucherService transactionVoucherService;

    //Initiate Transaction
    @PostMapping("/initiate")
    public ResponseEntity<TransactionVoucherResponse> initiateTransaction(@RequestBody TransactionVoucherInitiatorRequest request) {
        return new ResponseEntity<>(transactionVoucherService.initiateTransaction(request), HttpStatus.CREATED);
    }

    //First Approve Transaction
    @PostMapping("/first-approve")
    public ResponseEntity<TransactionVoucherResponse> firstApproveTransaction(@RequestBody ApproverRequest request) {
        System.out.println("First Approve Request "+ request.toString());
        return ResponseEntity.ok(transactionVoucherService.firstApproveTransaction(request));
    }

    //Bulk First Approve Transaction
    @PostMapping("/bulk-first-approve")
    public ResponseEntity<List<TransactionVoucherResponse>> bulkFirstApproveTransaction(@RequestBody List<ApproverRequest> requests) {
        return ResponseEntity.ok(transactionVoucherService.bulkFirstApproveTransaction(requests));
    }

    //Second Approve Transaction
    @PostMapping("/second-approve")
    public ResponseEntity<TransactionVoucherResponse> secondApproveTransaction(@RequestBody ApproverRequest request) throws ParseException, JsonProcessingException {
        return ResponseEntity.ok(transactionVoucherService.secondApproveTransaction(request));
    }

    //Bulk Second Approve Transaction
    @PostMapping("/bulk-second-approve")
    public ResponseEntity<List<TransactionVoucherResponse>> bulkSecondApproveTransaction(@RequestBody List<ApproverRequest> requests) throws JsonProcessingException, ParseException {
        return ResponseEntity.ok(transactionVoucherService.bulkSecondApproveTransaction(requests));
    }

    //Get All Transactions
    @GetMapping("/all")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactions() {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactions());
    }

    //Get Transaction By id
    @GetMapping("/{id}")
    public ResponseEntity<TransactionVoucherResponse> getTransactionById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.getTransactionById(id));
    }

    //Get All Transactions By Branch
    @GetMapping("/all-by-branch/{branchId}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByBranch(branchId));
    }

    //Get All Transactions By Vault
    @GetMapping("/all-by-vault/{vaultId}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByVault(vaultId));
    }

    //Get All Transactions By Initiator
    @GetMapping("/all-by-initiator/{initiator}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByInitiator(@PathVariable String initiator) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByInitiator(initiator));
    }

    //Get All Transactions By First Approver
    @GetMapping("/all-by-first-approver/{firstApprover}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsByFirstApprover(@PathVariable String firstApprover) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsByFirstApprover(firstApprover));
    }

    //Get All Transactions By Second Approver
    @GetMapping("/all-by-second-approver/{secondApprover}")
    public ResponseEntity<List<TransactionVoucherResponse>> getAllTransactionsBySecondApprover(@PathVariable String secondApprover) {
        return ResponseEntity.ok(transactionVoucherService.getAllTransactionsBySecondApprover(secondApprover));
    }

    //Initiator Update the Transaction If it is not yet approved or got rejected by the first approver
    @PutMapping("/update")
    public ResponseEntity<TransactionVoucherResponse> updateTransaction(@RequestBody TransactionVoucherUpdateRequest request) {
        return ResponseEntity.ok(transactionVoucherService.updateTransactionVoucher(request));
    }

    //Delete Transaction
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionVoucherService.deleteTransaction(id));
    }

    //Get CMS permissions
    @GetMapping("/permissions/{userId}")
    public ResponseEntity<List<CmsVaultPermission>> getCmsPermissions(@PathVariable String userId) {
        return ResponseEntity.ok(transactionVoucherService.getCmsVaultPermissionByUserId(userId));
    }

    //get authorization by user id
    @GetMapping("/authorizations/{userId}")
    public ResponseEntity<List<Authorisation>> getAuthorizations(@PathVariable String userId) {
        return ResponseEntity.ok(transactionVoucherService.getAuthorisationByUserId(userId));
    }

    //get first approver by branchId
    @GetMapping("/first-approvers/{branchId}")
    public ResponseEntity<List<User>> findFirstApprover(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.findAllByBranchIdAndAuthLevel(branchId, "First Approver"));
    }

    //get second approver by branchId
    @GetMapping("/second-approvers/{branchId}")
    public ResponseEntity<List<User>> secondFirstApprover(@PathVariable String branchId) {
        return ResponseEntity.ok(transactionVoucherService.findAllByBranchIdAndAuthLevel(branchId, "Second Approver"));
    }

}
