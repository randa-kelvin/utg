package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.FinancialOutFlowRequestDTO;
import com.untucapital.usuite.utg.dto.response.FinancialOutFlowResponseDTO;
import com.untucapital.usuite.utg.model.FinancialOutFlow;
import com.untucapital.usuite.utg.repository.FinancialOutFlowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class FinancialOutFlowService {
    @Autowired
    FinancialOutFlowRepository financialOutFlowRepository;

    @Transactional(value = "transactionManager")
    public void addOutflow(FinancialOutFlowRequestDTO request){

       FinancialOutFlow financialOutFlow  = new FinancialOutFlow();
       BeanUtils.copyProperties(request, financialOutFlow);

        financialOutFlowRepository.save(financialOutFlow);
    }

    @Transactional(value = "transactionManager")
    public List<FinancialOutFlowResponseDTO> getOutflow(String loanId){

        List<FinancialOutFlowResponseDTO> response = new ArrayList<>();
        List<FinancialOutFlow> financialOutFlowList = financialOutFlowRepository.findFinancialOutFlowByLoanId(loanId);

        for(FinancialOutFlow financialOutFlow: financialOutFlowList){

            FinancialOutFlowResponseDTO responseDTO = new FinancialOutFlowResponseDTO();
            BeanUtils.copyProperties(financialOutFlow, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteOutflow(String id){
        financialOutFlowRepository.deleteById(id);
    }
}
