package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.FinancialInflowRequestDTO;
import com.untucapital.usuite.utg.dto.response.FinancialInflowResponseDTO;
import com.untucapital.usuite.utg.model.FinancialInflow;
import com.untucapital.usuite.utg.repository.FinancialInflowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class FinancialInflowService {
    @Autowired
    FinancialInflowRepository financialInflowRepository;

    @Transactional(value = "transactionManager")
    public void addInflow(FinancialInflowRequestDTO requestDTO){

        FinancialInflow financialInflow = new FinancialInflow();
        BeanUtils.copyProperties(requestDTO, financialInflow);
        financialInflowRepository.save(financialInflow);
    }

    @Transactional(value = "transactionManager")
    public List<FinancialInflowResponseDTO> getInflow(String loanId){

        List<FinancialInflowResponseDTO> response = new ArrayList<>();
        List<FinancialInflow> financialInflowList = financialInflowRepository.findFinancialInflowByLoanId(loanId);

        for(FinancialInflow financialInflow : financialInflowList){

            FinancialInflowResponseDTO responseDTO = new FinancialInflowResponseDTO();
            BeanUtils.copyProperties(financialInflow, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteInflow(String id){
        financialInflowRepository.deleteById(id);
    }


}
