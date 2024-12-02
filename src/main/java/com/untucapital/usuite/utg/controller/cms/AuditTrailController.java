package com.untucapital.usuite.utg.controller.cms;

import com.untucapital.usuite.utg.model.cms.CmsAuditTrail;
import com.untucapital.usuite.utg.service.cms.CmsAuditTrailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@RestController
@RequestMapping("/cms/audit_trail")
@RequiredArgsConstructor
public class AuditTrailController {

    private final CmsAuditTrailService cmsAuditTrailService;

//    @GetMapping("/all")
//    public ResponseEntity<Iterable<AuditTrailResponseDTO>> getAllAuditTrails() {
//        return ResponseEntity.ok(cmsAuditTrailService.getAllAuditTrails());
//    }

    @GetMapping("/all")
    public ResponseEntity<List<CmsAuditTrail>> getAllAuditTrails() {
        return ResponseEntity.ok(cmsAuditTrailService.getAllAuditTrails());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<AuditTrailResponseDTO> getAuditTrailById(@PathVariable Integer id) {
//        return ResponseEntity.ok(cmsAuditTrailService.getAuditTrailById(id));
//    }

    @PostMapping("/save")
    public ResponseEntity<CmsAuditTrail> saveCmsAuditTrail(@RequestBody CmsAuditTrail cmsAuditTrail) {
        return ResponseEntity.ok(cmsAuditTrailService.saveCmsAuditTrail(cmsAuditTrail));
    }
//
//    //Add Initiator
//    @PostMapping("/add-initiator")
//    public ResponseEntity<AuditTrailResponseDTO> addInitiator(@RequestBody AuditTrailInitiatorRequest request) {
//        return ResponseEntity.ok(cmsAuditTrailService.addInitiator(request));
//    }
//
//    //Add First Approver
//    @PutMapping("/add-first-approver")
//    public ResponseEntity<AuditTrailResponseDTO> addFirstApprover(@RequestBody ApproverRequest request) {
//        return ResponseEntity.ok(cmsAuditTrailService.addFirstApprover(request));
//    }
//
//    //Add Second Approver
//    @PutMapping("/add-second-approver")
//    public ResponseEntity<AuditTrailResponseDTO> addSecondApprover(@RequestBody ApproverRequest request) {
//        return ResponseEntity.ok(cmsAuditTrailService.addSecondApprover(request));
//    }
//
//    //Delete Audit Trail
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteAuditTrail(@PathVariable Integer id) {
//
//        return ResponseEntity.ok(cmsAuditTrailService.deleteAuditTrail(id));
//    }
//
//    //Update Audit Trail
//    @PutMapping("/update")
//    public ResponseEntity<AuditTrailResponseDTO> updateAuditTrail(@RequestBody ChangeAmountRequest request) {
//        return ResponseEntity.ok(cmsAuditTrailService.updateAmount(request));
//    }

}
