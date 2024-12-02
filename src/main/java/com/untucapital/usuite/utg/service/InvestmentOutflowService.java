package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.InvestmentOutflowRequestDTO;
import com.untucapital.usuite.utg.dto.response.InvestmentOutflowResponseDTO;
import com.untucapital.usuite.utg.model.InvestmentOutflow;
import com.untucapital.usuite.utg.repository.InvestmentOutflowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class InvestmentOutflowService {

    @Autowired
    InvestmentOutflowRepository investmentOutflowRepository;

    @Transactional(value = "transactionManager")
    public void add(InvestmentOutflowRequestDTO request){

        InvestmentOutflow investmentOutflow = new InvestmentOutflow();
        BeanUtils.copyProperties(request, investmentOutflow);
        investmentOutflowRepository.save(investmentOutflow);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        investmentOutflowRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<InvestmentOutflowResponseDTO> get(String loanId){

        List<InvestmentOutflowResponseDTO> response = new ArrayList<>();
        List<InvestmentOutflow> investmentOutfowList = investmentOutflowRepository.findInvestmentOutflowByLoanId(loanId);

        for (InvestmentOutflow investmentOutfow: investmentOutfowList){
            InvestmentOutflowResponseDTO responseDTO = new InvestmentOutflowResponseDTO();
            BeanUtils.copyProperties(investmentOutfow, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }
}
