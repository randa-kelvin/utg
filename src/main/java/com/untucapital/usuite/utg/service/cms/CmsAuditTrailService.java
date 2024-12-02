package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.cms.CmsAuditTrail;
import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.repository.cms.CmsAuditTrailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Service
@RequiredArgsConstructor
public class CmsAuditTrailService {
    private final CmsAuditTrailRepository cmsAuditTrailRepository;

    //Get All
    @Transactional(value = "transactionManager")
    public List<CmsAuditTrail> getAllAuditTrails(){
        return cmsAuditTrailRepository.findAll();
    }
    //Get By Id
//    public CmsAuditTrail getAuditTrailById(String id){
//        return cmsAuditTrailRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//    }

    @Transactional(value = "transactionManager")
    public CmsAuditTrail saveCmsAuditTrail(CmsAuditTrail cmsAuditTrail){
        return cmsAuditTrailRepository.save(cmsAuditTrail);
    }

    //Add Initiator
//    public AuditTrail addInitiator(AuditTrailInitiatorRequest request){
//        AuditTrail auditTrail = AuditTrail.builder()
//                .initiator(request.getInitiator())
//                .amount(request.getAmount())
//                .fromVault(request.getFromVault())
//                .toVault(request.getToVault())
//                .initiatedAt(LocalDateTime.now())
//                .build();
//        return auditTrailRepository.save(auditTrail);
//    }
//
//    //Add First Approver
//    @Transactional(value = "transactionManager")
//    public AuditTrail addFirstApprover(ApproverRequest request){
//        AuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
////        auditTrail.setFirstApprover(request.getApprover());
//        auditTrail.setFirstApprovedAt(LocalDateTime.now());
//        return auditTrailRepository.save(auditTrail);
//    }
//
//    //Add Second Approver
//    @Transactional(value = "transactionManager")
//    public AuditTrail addSecondApprover(ApproverRequest request){
//
//        AuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
////        auditTrail.setSecondApprover(request.getApprover());
//        auditTrail.setSecondApprovedAt(LocalDateTime.now());
//
//        return auditTrailRepository.save(auditTrail);
//    }
//
//    //Delete Audit Trail
//    @Transactional(value = "transactionManager")
//    public String deleteAuditTrail(Integer id){
//
//        AuditTrail auditTrail = auditTrailRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
//        auditTrailRepository.delete(auditTrail);
//        return String.format("Audit Trail %d Deleted".formatted(id));
//    }
//
//    //Update Audit Trail
//    @Transactional(value = "transactionManager")
//    public AuditTrail updateAmount(ChangeAmountRequest request){
//        AuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
//        if(auditTrail.getFirstApprover() == null){
//            if (request.getAmount() != null && !request.getAmount().equals(auditTrail.getAmount())){
//                auditTrail.setAmount(request.getAmount());
//                return auditTrailRepository.save(auditTrail);
//            }
//        }
//        else {
//            throw new RuntimeException("Audit Trail has been approved");
//        }
//
//        return auditTrail;
//    }
}
