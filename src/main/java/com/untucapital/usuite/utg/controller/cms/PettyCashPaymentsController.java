package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.dto.cms.req.PettyCashPaymentsRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.PettyCashPaymentsResponseDTO;
import com.untucapital.usuite.utg.model.cms.PettyCashPayments;
import com.untucapital.usuite.utg.service.cms.PettyCashPaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @created 10/9/2023
 */

@RestController
@RequestMapping("/cms/petty-cash-payments")
@RequiredArgsConstructor
public class PettyCashPaymentsController {

    private final PettyCashPaymentsService pettyCashPaymentsService;

    @GetMapping("/all")
    public ResponseEntity<List<PettyCashPaymentsResponseDTO>> getAllPettyCashPayments(){
        return ResponseEntity.ok(pettyCashPaymentsService.getAllPettyCashPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PettyCashPaymentsResponseDTO> getPettyCashPaymentsById(@PathVariable String id){
        return ResponseEntity.ok(pettyCashPaymentsService.getPettyCashPaymentsById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PettyCashPaymentsResponseDTO> updatePettyCashPayments(
            @PathVariable("id") String id, @RequestBody PettyCashPaymentsRequestDTO pettyCashPayments) {
        PettyCashPaymentsResponseDTO updatedPettyCashPayments = pettyCashPaymentsService.updatePettyCashPayments(id, pettyCashPayments);
        if (updatedPettyCashPayments != null) {
            return new ResponseEntity<>(updatedPettyCashPayments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
