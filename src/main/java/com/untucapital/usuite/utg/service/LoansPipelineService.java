package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.LoTotalPipelineAndDisbursementsDTO;
import com.untucapital.usuite.utg.dto.LoanOfficerProductivityDTO;
import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import com.untucapital.usuite.utg.dto.request.LoansPipelineRequestDTO;
import com.untucapital.usuite.utg.dto.response.LoansPipelineResponseDTO;
import com.untucapital.usuite.utg.model.LoansPipeline;
import com.untucapital.usuite.utg.processor.LoanPipelineMapper;
import com.untucapital.usuite.utg.repository.LoansPipelineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class LoansPipelineService {

    private final LoansPipelineRepository loansPipelineRepository;
    private final LoanPipelineMapper loanPipelineMapper;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public LoansPipelineResponseDTO createLoan(LoansPipelineRequestDTO request) {

        LoansPipeline loansPipeline = new LoansPipeline();
        LoansPipelineResponseDTO response = new LoansPipelineResponseDTO();
        BeanUtils.copyProperties(request, loansPipeline);
        log.info("loan pipeline: {}", loansPipeline);
        LoansPipeline savedLoan = loansPipelineRepository.save(loansPipeline);
        BeanUtils.copyProperties(savedLoan, response);

        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LoansPipelineResponseDTO> getAllLoans() {

        List<LoansPipelineResponseDTO> response = new ArrayList<>();
        List<LoansPipeline> loansPipelineList = loansPipelineRepository.findAll();

        for (LoansPipeline loansPipeline : loansPipelineList) {
            LoansPipelineResponseDTO loanResponseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(loansPipeline, loanResponseDTO);

            response.add(loanResponseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public LoansPipelineResponseDTO getLoanById(String id) {
        LoansPipelineResponseDTO responseDTO = null;
        Optional<LoansPipeline> optionalLoan = loansPipelineRepository.findLoansPipelineById(id);

        if (optionalLoan.isPresent()) {
            LoansPipeline loan = optionalLoan.get();
            responseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(loan, responseDTO);
        }

        return responseDTO;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public LoansPipelineResponseDTO updateLoan(LoansPipelineResponseDTO existingLoan) {
        // Retrieve the loan entity from the database using its ID
        Optional<LoansPipeline> optionalLoan = loansPipelineRepository.findLoansPipelineById(existingLoan.getId());

        // Check if the loan exists
        if (optionalLoan.isPresent()) {
            // If the loan exists, update its properties
            LoansPipeline loan = optionalLoan.get();
            BeanUtils.copyProperties(existingLoan, loan);

            // Save the updated loan entity
            LoansPipeline savedLoan = loansPipelineRepository.save(loan);

            // Create a response DTO and copy properties from the saved entity
            LoansPipelineResponseDTO responseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(savedLoan, responseDTO);

            return responseDTO;
        } else {
            // If the loan does not exist, return null or throw an exception as appropriate
            return null; // or throw new SomeException("Loan not found");
        }
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public Integer getCountByLoanOfficer(String loanOfficer) {
        int count = loansPipelineRepository.countByLoanOfficerAndLoanStatus(loanOfficer, "Disbursed");
        return count;
    }

//    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
//    public List<LoansPipeline> getLoPipeline(String userId) {
//        List<LoansPipeline> loansPipelineList = loansPipelineRepository.findLoansPipelineByLoanOfficer(userId);
//        return loansPipelineList;
//    }


    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LoansPipelineResponseDTO> getLoPipeline(String userId) {

        List<LoansPipelineResponseDTO> response = new ArrayList<>();
        List<LoansPipeline> loansPipelineList = loansPipelineRepository.findLoansPipelineByLoanOfficer(userId);

        for (LoansPipeline loansPipeline : loansPipelineList) {
            LoansPipelineResponseDTO loanResponseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(loansPipeline, loanResponseDTO);

            response.add(loanResponseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LoansPipelineResponseDTO> getPipelineByBranch(String branchName) {

        List<LoansPipelineResponseDTO> response = new ArrayList<>();
        List<LoansPipeline> loansPipelineList = loansPipelineRepository.findByBranchNameOrderByLoanOfficerAsc(branchName);

        for (LoansPipeline loansPipeline : loansPipelineList) {
            LoansPipelineResponseDTO loanResponseDTO = new LoansPipelineResponseDTO();
            BeanUtils.copyProperties(loansPipeline, loanResponseDTO);

            response.add(loanResponseDTO);
        }
        return response;
    }



    public List<LoansPipelineDTO> getLoanPipelineSummary() {
        List<Object[]> arrayObject  = loansPipelineRepository.getLoanPipelineSummary();
        List<LoansPipelineDTO> response = LoanPipelineMapper.mapToDTO(arrayObject);
        return response;
    }

    public List<LoanOfficerProductivityDTO> getLoanOfficerProductivity() {
        List<Object[]> arrayObject  = loansPipelineRepository.getLoanOfficerProductivity();
        List<LoanOfficerProductivityDTO> response = LoanPipelineMapper.mapToLoanOfficerProductivityDTO(arrayObject);
        return response;
    }

    public List<LoTotalPipelineAndDisbursementsDTO> getLoTotalPipelineAndDisbursements() {
        List<Object[]> arrayObject  = loansPipelineRepository.getLoTotalPipelineAndDisbursements();
        List<LoTotalPipelineAndDisbursementsDTO> response = LoanPipelineMapper.mapTogetLoTotalPipelineAndDisbursements(arrayObject);
        return response;
    }

    public List<LoTotalPipelineAndDisbursementsDTO> getLoTotalPipelineAndDisbursementsByBranch(String branchName) {
        List<Object[]> arrayObject  = loansPipelineRepository.getLoTotalPipelineAndDisbursementsByBranch(branchName);
        List<LoTotalPipelineAndDisbursementsDTO> response = LoanPipelineMapper.mapTogetLoTotalPipelineAndDisbursements(arrayObject);
        return response;
    }


    public List<LoanOfficerProductivityDTO> getLoanOfficerProductivityByBranch(String branch) {
        List<Object[]> arrayObject  = loansPipelineRepository.getLoanOfficerProductivityByBranch(branch);
        List<LoanOfficerProductivityDTO> response = LoanPipelineMapper.mapToLoanOfficerProductivityDTO(arrayObject);
        return response;
    }

}
