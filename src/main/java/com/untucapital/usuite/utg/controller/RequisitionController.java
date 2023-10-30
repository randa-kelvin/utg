package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.DTO.response.RequisitionResponseDTO;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.Requisitions;
import com.untucapital.usuite.utg.repository.RequisitionRepository;
import com.untucapital.usuite.utg.service.RequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/requisitions")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    private static final Logger log = LoggerFactory.getLogger(ClientLoanController.class);

    @GetMapping
    public List<RequisitionResponseDTO> list() {
        return requisitionService.getAllRequistions();
    }

    @PostMapping
    public void saveRequisitions(@RequestBody RequisitionResponseDTO requisitions) {
        log.info(String.valueOf(requisitions));
        requisitionService.saveRequisition(requisitions);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        requisitionService.deleteRequisition(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisitionResponseDTO> getRequisitionById(@PathVariable("id") String id) {
        Optional<RequisitionResponseDTO> requisition = requisitionService.getRequisitionById(id);

        if (requisition.isPresent()) {
            return new ResponseEntity<>(requisition.get(), HttpStatus.OK);
        } else {
            // Handle the case when the Requisitions object is not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("getByPoNumber/{poNumber}")
    public ResponseEntity<Requisitions> getRequisitionByPoNumber(@PathVariable("poNumber") String poNumber) {
        Optional<Requisitions> requisitions = requisitionService.getRequisitionByPoNumber(poNumber);

        if (requisitions.isPresent()) {
            return new ResponseEntity<>(requisitions.get(), HttpStatus.OK);
        } else {
            // Handle the case when no Requisitions objects are found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRequisition(@PathVariable("id") String id, @RequestBody Requisitions updatedRequisition) {

        // Check if the requisition with the given ID exists
        Optional<RequisitionResponseDTO> existingRequisitionOptional = requisitionService.getRequisitionById(id);

        if (!existingRequisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build(); // Return a 404 response if not found
        }

        RequisitionResponseDTO existingRequisition = existingRequisitionOptional.get(); // Extract the actual object

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

        // Save the updated requisition
        requisitionService.saveRequisition(existingRequisition);

        return ResponseEntity.ok("Requisition updated successfully"); // Return a success response
    }


    @DeleteMapping("/attachments/{requisitionId}/{attachmentIndex}")
    public ResponseEntity<String> deleteAttachment(
            @PathVariable String requisitionId,
            @PathVariable int attachmentIndex) {

        // Find the Requisitions entity by ID
        Optional<RequisitionResponseDTO> requisitionOptional = requisitionService.getRequisitionById(requisitionId);

        if (!requisitionOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        RequisitionResponseDTO requisition = requisitionOptional.get();

        // Check if the attachment index is valid
        List<String> attachments = requisition.getAttachments();
        if (attachmentIndex >= 0 && attachmentIndex < attachments.size()) {
            // Remove the attachment from the list
            attachments.remove(attachmentIndex);

            // Update the Requisitions entity
            requisitionService.saveRequisition(requisition);

            return ResponseEntity.ok("Attachment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid attachment index");
        }
    }





}
