package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.LoTotalPipelineAndDisbursementsDTO;
import com.untucapital.usuite.utg.dto.LoanOfficerProductivityDTO;
import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import com.untucapital.usuite.utg.dto.request.LoansPipelineRequestDTO;
import com.untucapital.usuite.utg.dto.response.LoansPipelineResponseDTO;
import com.untucapital.usuite.utg.service.LoansPipelineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "credit_application_pipeline")
public class LoansPipelineController {

    private final LoansPipelineService loansPipelineService;


    @PostMapping("/loans")
    public ResponseEntity<LoansPipelineResponseDTO> createLoan(@RequestBody LoansPipelineRequestDTO loansPipeline) {
        log.info("request: {}", loansPipeline);
        LoansPipelineResponseDTO savedLoan = loansPipelineService.createLoan(loansPipeline);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<LoansPipelineResponseDTO> updateLoan(@PathVariable("id") String id, @RequestBody LoansPipelineResponseDTO updatedLoan) {
        log.info("Updating loan with ID: {}", id);
        LoansPipelineResponseDTO existingLoan = loansPipelineService.getLoanById(id);

        if (existingLoan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update existing loan fields
        existingLoan.setUserId(updatedLoan.getUserId());
        existingLoan.setBranchName(updatedLoan.getBranchName());
        existingLoan.setDateRecorded(updatedLoan.getDateRecorded());
        existingLoan.setApplicant(updatedLoan.getApplicant());
        existingLoan.setSector(updatedLoan.getSector());
        existingLoan.setRepeatClient(updatedLoan.getRepeatClient());
        existingLoan.setSoughtLoan(updatedLoan.getSoughtLoan());
        existingLoan.setLoanStatus(updatedLoan.getLoanStatus());
        existingLoan.setLoanOfficer(updatedLoan.getLoanOfficer());

        LoansPipelineResponseDTO savedLoan = loansPipelineService.updateLoan(existingLoan);
        return new ResponseEntity<>(savedLoan, HttpStatus.OK);
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoansPipelineResponseDTO>> getAllLoans() {
        List<LoansPipelineResponseDTO> loans = loansPipelineService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/loans/count/{loanOfficer}")
    public ResponseEntity<Integer> getCountByLoanOfficer(@PathVariable("loanOfficer") String loanOfficer) {
        Integer count = loansPipelineService.getCountByLoanOfficer(loanOfficer);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

//    @GetMapping("/getLoPipeline/{userId}")
//    public List<LoansPipeline> getLoPipeline(@PathVariable("userId") String userId) {
//        return loansPipelineService.getLoPipeline(userId);
//
//    }

    @GetMapping("/getLoPipeline/{userId}")
    public ResponseEntity<List<LoansPipelineResponseDTO>> getLoPipeline(@PathVariable("userId") String userId) {
        List<LoansPipelineResponseDTO> loans = loansPipelineService.getLoPipeline(userId);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/getPipelineByBranch/{branchName}")
    public ResponseEntity<List<LoansPipelineResponseDTO>> getPipelineByBranch(@PathVariable("branchName") String branchName) {
        List<LoansPipelineResponseDTO> loans = loansPipelineService.getPipelineByBranch(branchName);
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/getPipelineSummary")
    public List<LoansPipelineDTO> getLoanPipelineSummary() {
        return loansPipelineService.getLoanPipelineSummary();
    }

    @GetMapping("/getLoanOfficerProductivity")
    public List<LoanOfficerProductivityDTO> getLoanOfficerProductivity() {
        return loansPipelineService.getLoanOfficerProductivity();
    }

    @GetMapping("/getLoanOfficerProductivityByBranch/{branch}")
    public List<LoanOfficerProductivityDTO> getLoanOfficerProductivityByBranch(@PathVariable("branch") String branch) {
        return loansPipelineService.getLoanOfficerProductivityByBranch(branch);
    }

    @GetMapping("/getLoTotalPipelineAndDisbursements")
    public List<LoTotalPipelineAndDisbursementsDTO> getLoTotalPipelineAndDisbursements() {
        return loansPipelineService.getLoTotalPipelineAndDisbursements();
    }

    @GetMapping("/getLoTotalPipelineAndDisbursementsByBranch/{branchName}")
    public List<LoTotalPipelineAndDisbursementsDTO> getLoTotalPipelineAndDisbursementsByBranch(@PathVariable String branchName) {
        return loansPipelineService.getLoTotalPipelineAndDisbursementsByBranch(branchName);
    }

}
