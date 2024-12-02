package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.InterestAndFinancialCostRequestDTO;
import com.untucapital.usuite.utg.dto.response.InterestAndFinancialCostResponseDTO;
import com.untucapital.usuite.utg.model.InterestAndFinancialCost;
import com.untucapital.usuite.utg.repository.InterestAndFinancialCostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class InterestAndFinancialCostService {
    @Autowired
    InterestAndFinancialCostRepository interestAndFinancialCostRepository;

    @Transactional(value = "transactionManager")
    public List<InterestAndFinancialCostResponseDTO> findByLoanId(String loanId){

        List<InterestAndFinancialCostResponseDTO> response = new ArrayList<InterestAndFinancialCostResponseDTO>();
        List<InterestAndFinancialCost> interestAndFinancialCostList= interestAndFinancialCostRepository.findInterestAndFinancialCostByLoanId(loanId);

        for (InterestAndFinancialCost interestAndFinancialCost: interestAndFinancialCostList){
            InterestAndFinancialCostResponseDTO responseDTO = new InterestAndFinancialCostResponseDTO();
            BeanUtils.copyProperties(interestAndFinancialCost, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void save(InterestAndFinancialCostRequestDTO request){

        InterestAndFinancialCost interestAndFinancialCost = new InterestAndFinancialCost();
        BeanUtils.copyProperties(request, interestAndFinancialCost);
        interestAndFinancialCostRepository.save(interestAndFinancialCost);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        interestAndFinancialCostRepository.deleteById(id);
    }
}
