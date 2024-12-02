package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.RequisitionsRequestDTO;
import com.untucapital.usuite.utg.dto.response.RequisitionsResponseDTO;
//import com.untucapital.usuite.utg.model.RequisitionsResponseDTO;
import com.untucapital.usuite.utg.service.RequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisitions")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    @GetMapping
    public List<RequisitionsResponseDTO> list() {
        return requisitionService.getAllRequistions();
    }


    @GetMapping("userid/{id}")
    public List<RequisitionsResponseDTO> getRequisitionByUserId(@PathVariable("id") String id) {
        List<RequisitionsResponseDTO> requisition = requisitionService.getRequisitionByUserId(id);
        return requisition;
    }

    @GetMapping("approverId/{userid}")
    public List<RequisitionsResponseDTO> getRequisitionByApproverId(@PathVariable("userid") String userId) {
        List<RequisitionsResponseDTO> requisition = requisitionService.getRequisitionByApproverId(userId);
        return requisition;
    }

    @PostMapping("/save")
    public ResponseEntity<RequisitionsResponseDTO> saveRequisitions(@RequestBody RequisitionsRequestDTO requisitions) {
        RequisitionsResponseDTO savedRequisition = requisitionService.saveRequisition(requisitions);
        log.info("Saved requisition: {}", savedRequisition);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRequisition);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        requisitionService.deleteRequisition(id);
    }

    @GetMapping("/{id}")
    public RequisitionsResponseDTO getRequisitionById(@PathVariable("id") String id) {

        RequisitionsResponseDTO requisition = requisitionService.getRequisitionById(id);

        log.info("Requisition:{}", requisition);
        return requisition;

//        if (requisition!=null) {
//            return new ResponseEntity<>(requisition, HttpStatus.OK);
//        } else {
//            // Handle the case when the RequisitionsResponseDTO object is not found
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping("financeApproval")
    public List<RequisitionsResponseDTO> getRequisitionsToBeAprrovedByFinance() {
        List<RequisitionsResponseDTO> requisition = requisitionService.getRequisitionsToBeApprovedByFinance();
        return requisition;
    }

    @GetMapping("approvedByFinance")
    public List<RequisitionsResponseDTO> getRequisitionsApprovedByFinance() {
        List<RequisitionsResponseDTO> requisition = requisitionService.getRequisitionsApprovedByFinance();
        return requisition;
    }

    @GetMapping("getByTeller/{tellerId}")
    public List<RequisitionsResponseDTO> getRequisitionsByTellerId(@PathVariable("tellerId") String tellerId) {
        List<RequisitionsResponseDTO> requisition = requisitionService.getRequisitionsByTellerId(tellerId);
        return requisition;
    }


    @GetMapping("getByPoNumber/{poNumber}")
    public ResponseEntity<RequisitionsResponseDTO> getRequisitionByPoNumber(@PathVariable("poNumber") String poNumber) {
        RequisitionsResponseDTO requisitions = requisitionService.getRequisitionByPoNumber(poNumber);

        if (requisitions !=null) {
            return new ResponseEntity<>(requisitions, HttpStatus.OK);
        } else {
            // Handle the case when no RequisitionsResponseDTO objects are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequisition(@PathVariable("id") String id, @RequestBody RequisitionsRequestDTO updatedRequisition) {

        RequisitionsRequestDTO requisitionsRequest = new RequisitionsRequestDTO();

        // Check if the requisition with the given ID exists
        RequisitionsResponseDTO existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (existingRequisitionOptional==null) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionsResponseDTO existingRequisition = existingRequisitionOptional; // Extract the actual object

        // Update the existing requisition with the new data
        existingRequisition.setNotes(updatedRequisition.getNotes());


        // Update the existing approvers with the new approvers
        existingRequisition.setApprovers(updatedRequisition.getApprovers());

//        // Update the existing approvers with the new approvers
//        existingRequisition.setAttachments(updatedRequisition.getAttachments());

        // Append the new attachments to the existing ones
        List<String> existingAttachments = existingRequisition.getAttachments();
        List<String> updatedAttachments = updatedRequisition.getAttachments();
        if (existingAttachments != null && updatedAttachments != null) {
            existingAttachments.addAll(updatedAttachments);
        } else if (updatedAttachments != null) {
            existingAttachments = updatedAttachments;
        }
        existingRequisition.setAttachments(existingAttachments);

        BeanUtils.copyProperties(existingRequisition, requisitionsRequest);

        // Save the updated requisition
        requisitionService.saveRequisition(requisitionsRequest);

        return ResponseEntity.ok("Requisition updated successfully"); // Return a success response
    }

    @PutMapping("poApproveRequest/{id}")
    public ResponseEntity<String> updateRequisitionApproveRequest(@PathVariable("id") String id, @RequestBody RequisitionsRequestDTO updatedRequisition) {

        RequisitionsRequestDTO requisitionsRequest = new RequisitionsRequestDTO();

        // Check if the requisition with the given ID exists
        RequisitionsResponseDTO existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (existingRequisitionOptional==null) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionsResponseDTO existingRequisition = existingRequisitionOptional; // Extract the actual object

        // Update the existing requisition with the new data
        existingRequisition.setPoApprover(updatedRequisition.getPoApprover());
        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());

        BeanUtils.copyProperties(existingRequisition, requisitionsRequest);

        // Save the updated requisition
        requisitionService.saveRequisition(requisitionsRequest);

        return ResponseEntity.ok("Requisition approved successfully"); // Return a success response
    }

    @PutMapping("cmsApproveRequest/{id}")
    public ResponseEntity<String> updateRequisitionCmsApproveRequest(@PathVariable("id") String id, @RequestBody RequisitionsRequestDTO updatedRequisition) {

        RequisitionsRequestDTO requisitionsRequest = new RequisitionsRequestDTO();

        // Check if the requisition with the given ID exists
        RequisitionsResponseDTO existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (existingRequisitionOptional==null) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }
        RequisitionsResponseDTO existingRequisition = existingRequisitionOptional; // Extract the actual object

        // Update the existing requisition with the new data
        existingRequisition.setCmsApprover(updatedRequisition.getCmsApprover());
        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());
        existingRequisition.setTeller(updatedRequisition.getTeller());

        BeanUtils.copyProperties(existingRequisition, requisitionsRequest);

        // Save the updated requisition
        requisitionService.saveRequisition(requisitionsRequest);

        return ResponseEntity.ok("Requisition approved successfully"); // Return a success response
    }

    @PutMapping("paidRequisition/{id}")
    public ResponseEntity<String> updateRequisitionPaidStatus(@PathVariable("id") String id, @RequestBody RequisitionsRequestDTO updatedRequisition) {

        RequisitionsRequestDTO requisitionsRequestDTO = new RequisitionsRequestDTO();

        // Check if the requisition with the given ID exists
        RequisitionsResponseDTO existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (existingRequisitionOptional==null) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionsResponseDTO existingRequisition = existingRequisitionOptional; // Extract the actual object

        // Update the existing requisition with the new data
        existingRequisition.setPoStatus(updatedRequisition.getPoStatus());

        BeanUtils.copyProperties(existingRequisition, requisitionsRequestDTO);

        // Save the updated requisition
        requisitionService.saveRequisition(requisitionsRequestDTO);

        return ResponseEntity.ok("Requisition paid successfully"); // Return a success response
    }


    @DeleteMapping("/attachments/{requisitionId}/{attachmentIndex}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable String requisitionId,
            @PathVariable int attachmentIndex) {

        // Find the RequisitionsResponseDTO entity by ID
        RequisitionsResponseDTO requisitionOptional = requisitionService.getRequisitionById(requisitionId);

        if (requisitionOptional ==null) {
            return ResponseEntity.notFound().build();
        }



        // Check if the attachment index is valid
        List<String> attachments = requisitionOptional.getAttachments();
        if (attachmentIndex >= 0 && attachmentIndex < attachments.size()) {
            // Remove the attachment from the list
            attachments.remove(attachmentIndex);

            RequisitionsRequestDTO request = new RequisitionsRequestDTO();
            BeanUtils.copyProperties(requisitionOptional, request);
            // Update the RequisitionsResponseDTO entity
            requisitionService.saveRequisition(request);

            return ResponseEntity.ok("Attachment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid attachment index");
        }
    }

//



}
