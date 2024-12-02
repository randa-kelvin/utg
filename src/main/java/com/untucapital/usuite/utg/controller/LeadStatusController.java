package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.LeadStatusRequestDTO;
import com.untucapital.usuite.utg.dto.response.LeadStatusResponseDTO;
import com.untucapital.usuite.utg.service.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/leadStatus")
public class LeadStatusController {
    @Autowired
    LeadStatusService leadStatusService;


    @GetMapping()
    public List<LeadStatusResponseDTO> list() {
        return leadStatusService.getAllZones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeadStatusResponseDTO> get(@PathVariable String id) {
        try {
            LeadStatusResponseDTO leadStatus = (LeadStatusResponseDTO) leadStatusService.getZoneById(id);
            return new ResponseEntity<LeadStatusResponseDTO>(leadStatus, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<LeadStatusResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLeadStatus")
    public void add(@RequestBody LeadStatusRequestDTO leadStatus) {
        leadStatusService.saveZones(leadStatus);
    }


    @DeleteMapping("/deleteLeadStatus/{id}")
    public void delete(@PathVariable String id) {
        leadStatusService.deleteZones(id);
    }
}
