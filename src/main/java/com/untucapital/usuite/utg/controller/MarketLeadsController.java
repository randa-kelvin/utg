package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.MarketLeadsRequestDTO;
import com.untucapital.usuite.utg.dto.response.MarketLeadsResponseDTO;
import com.untucapital.usuite.utg.repository.MarketLeadsRepository;
import com.untucapital.usuite.utg.service.MarketLeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketLeads")
public class MarketLeadsController {

    private final MarketLeadsService marketLeadsService;

    private final MarketLeadsRepository marketLeadsRepository;

    @Autowired
    public MarketLeadsController(MarketLeadsService marketLeadsService, MarketLeadsRepository marketLeadsRepository) {
        this.marketLeadsService = marketLeadsService;
        this.marketLeadsRepository = marketLeadsRepository;
    }

    @GetMapping
    public ResponseEntity<List<MarketLeadsResponseDTO>> getAllMarketLeads() {
        List<MarketLeadsResponseDTO> marketLeadsList = marketLeadsService.getAllMarketLeads();
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<MarketLeadsResponseDTO> createMarketLead(@RequestBody MarketLeadsRequestDTO marketLeads) {
        MarketLeadsResponseDTO createdMarketLeads = marketLeadsService.createMarketLeads(marketLeads);
        return new ResponseEntity<>(createdMarketLeads, HttpStatus.CREATED);
    }

    @GetMapping("/branch/{branch}")
    public ResponseEntity<List<MarketLeadsResponseDTO>> getMarketLeadsByBranch(@PathVariable String branch) {
        List<MarketLeadsResponseDTO> marketLeadsList = marketLeadsService.getMarketLeadsByBranch(branch);
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }

    @GetMapping("/loanOfficer/{loanOfficer}")
    public ResponseEntity<List<MarketLeadsResponseDTO>> getMarketLeadsByLoanOfficer(@PathVariable String loanOfficer) {
        List<MarketLeadsResponseDTO> marketLeadsList = marketLeadsService.getMarketLeadsByLoanOfficer(loanOfficer);
        return new ResponseEntity<>(marketLeadsList, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MarketLeadsResponseDTO> updateMarketLead(
            @PathVariable("id") String id, @RequestBody MarketLeadsRequestDTO marketLeads) {
        MarketLeadsResponseDTO updatedMarketLeads = marketLeadsService.updateMarketLeads(id, marketLeads);
        if (updatedMarketLeads != null) {
            return new ResponseEntity<>(updatedMarketLeads, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("status/{id}")
    public ResponseEntity<MarketLeadsResponseDTO> updateMarketLeadStatus(
            @PathVariable("id") String id, @RequestBody MarketLeadsRequestDTO marketLeads) {
        MarketLeadsResponseDTO updatedMarketLeadStatus = marketLeadsService.updateMarketLeadsStatus(id, marketLeads);
        if (updatedMarketLeadStatus != null) {
            return new ResponseEntity<>(updatedMarketLeadStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<MarketLeadsResponseDTO> getMarketLeadById(@PathVariable("id") String id) {
        MarketLeadsResponseDTO marketLeads = marketLeadsService.getMarketLeadsById(id);
        if (marketLeads != null) {
            return new ResponseEntity<>(marketLeads, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarketLead(@PathVariable("id") String id) {
        marketLeadsService.deleteMarketLeads(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
