package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.dto.cms.req.TransactionPurposeRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.TransactionPurposeResponseDTO;
import com.untucapital.usuite.utg.service.cms.TransactionPurposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("cms/transaction-purpose")
public class TransactionPurposeController {
    private final TransactionPurposeService transactionPurposeService;

    //SAVE
    @PostMapping("/save")
    public ResponseEntity<TransactionPurposeResponseDTO> saveTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        return ResponseEntity.ok(transactionPurposeService.save(request));
    }

    @PutMapping("/update")
    //UPDATE
    public ResponseEntity<TransactionPurposeResponseDTO> updateTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        return ResponseEntity.ok(transactionPurposeService.update(request));
    }

    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<TransactionPurposeResponseDTO>> getAllTransactionVoucher() {
        return ResponseEntity.ok(transactionPurposeService.getAll());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionPurposeResponseDTO> getTransactionVoucherById(@PathVariable Integer id) {
        return ResponseEntity.ok(transactionPurposeService.getById(id));
    }

    //DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTransactionVoucher(@RequestBody TransactionPurposeRequestDTO request) {
        transactionPurposeService.delete(request);
        return ResponseEntity.ok("Transaction Purpose Deleted Successfully");
    }
}
