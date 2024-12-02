package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.dto.ApproverRequest;
import com.untucapital.usuite.utg.dto.AuditTrailInitiatorRequest;
import com.untucapital.usuite.utg.dto.ChangeAmountRequest;
import com.untucapital.usuite.utg.dto.cms.res.AuditTrailResponseDTO;
import com.untucapital.usuite.utg.model.cms.CmsAuditTrail;
import com.untucapital.usuite.utg.repository.cms.CmsAuditTrailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Service
@RequiredArgsConstructor
public class AuditTrailService {
    private final CmsAuditTrailRepository auditTrailRepository;

    //Get All
    @Transactional(value = "transactionManager")
    public List<AuditTrailResponseDTO> getAllAuditTrails(){
        List<AuditTrailResponseDTO> response = new ArrayList<>();
        List<CmsAuditTrail> auditTrailList = auditTrailRepository.findAll();
        for (CmsAuditTrail trail : auditTrailList ){
            AuditTrailResponseDTO responseDTO = new AuditTrailResponseDTO();
            BeanUtils.copyProperties(trail, responseDTO);
            response.add(responseDTO);
        }
        return response;
    }
    //Get By Id
    public AuditTrailResponseDTO getAuditTrailById(Integer id){

        AuditTrailResponseDTO responseDTO = new AuditTrailResponseDTO();
        CmsAuditTrail auditTrail = auditTrailRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));

        BeanUtils.copyProperties(auditTrail, responseDTO);

        return responseDTO;
    }
    //Add Initiator
//    public AuditTrailResponseDTO addInitiator(AuditTrailInitiatorRequest request){
//
//        AuditTrailResponseDTO responseDTO = new AuditTrailResponseDTO();
//        CmsAuditTrail auditTrail = CmsAuditTrail.builder()
//                .initiator(request.getInitiator())
//                .amount(request.getAmount())
//                .fromVault(request.getFromVault())
//                .toVault(request.getToVault())
//                .initiatedAt(LocalDateTime.now())
//                .build();
//
//        CmsAuditTrail trail= auditTrailRepository.save(auditTrail);
//        BeanUtils.copyProperties(trail, responseDTO);
//
//        return  responseDTO;
//    }

    //Add First Approver
//    @Transactional(value = "transactionManager")
//    public AuditTrailResponseDTO addFirstApprover(ApproverRequest request){
//
//        AuditTrailResponseDTO response = new AuditTrailResponseDTO();
//        CmsAuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
////        auditTrail.setFirstApprover(request.getApprover());
//        auditTrail.setFirstApprovedAt(LocalDateTime.now());
//        CmsAuditTrail trail = auditTrailRepository.save(auditTrail);
//
//        BeanUtils.copyProperties(trail, response);
//
//        return response;
//    }
//
//    //Add Second Approver
//    @Transactional(value = "transactionManager")
//    public AuditTrailResponseDTO addSecondApprover(ApproverRequest request){
//
//        AuditTrailResponseDTO response = new AuditTrailResponseDTO();
//        CmsAuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
////        auditTrail.setSecondApprover(request.getApprover());
//        auditTrail.setSecondApprovedAt(LocalDateTime.now());
//
//        CmsAuditTrail trail = auditTrailRepository.save(auditTrail);
//        BeanUtils.copyProperties(trail,response);
//
//        return response;
//    }

    //Delete Audit Trail
    @Transactional(value = "transactionManager")
    public String deleteAuditTrail(Integer id){

        CmsAuditTrail auditTrail = auditTrailRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));

        auditTrailRepository.delete(auditTrail);
        return String.format("Audit Trail %d Deleted".formatted(id));
    }

    //Update Audit Trail
//    @Transactional(value = "transactionManager")
//    public AuditTrailResponseDTO updateAmount(ChangeAmountRequest request){
//
//        AuditTrailResponseDTO response = new AuditTrailResponseDTO();
//        CmsAuditTrail auditTrail = auditTrailRepository.findById(request.getId())
//                .orElseThrow(() -> new RuntimeException("Audit Trail not found"));
//
//        if(auditTrail.getFirstApprover() == null){
//            if (request.getAmount() != null && !request.getAmount().equals(auditTrail.getAmount())){
//                auditTrail.setAmount(request.getAmount());
//                CmsAuditTrail auditTrail1= auditTrailRepository.save(auditTrail);
//                BeanUtils.copyProperties(auditTrail1,response);
//
//                return response;
//            }
//        }
//        else {
//            throw new RuntimeException("Audit Trail has been approved");
//        }
//
//        BeanUtils.copyProperties(auditTrail,response);
//        return response;
//    }
}
