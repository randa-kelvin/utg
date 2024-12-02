package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.InvestmentInfowRequestDTO;
import com.untucapital.usuite.utg.dto.response.InvestmentInfowResponseDTO;
import com.untucapital.usuite.utg.model.InvestmentInfow;
import com.untucapital.usuite.utg.repository.InvestmentInfowRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class InvestmentInflowService {
    @Autowired
    InvestmentInfowRepository investmentInfowRepository;

    @Transactional(value = "transactionManager")
    public List<InvestmentInfowResponseDTO> findByLoanId(String loanId){

        List<InvestmentInfowResponseDTO> response = new ArrayList<>();
        List<InvestmentInfow> investmentInfowList = investmentInfowRepository.findInvestmentInfowByLoanId(loanId);

        for (InvestmentInfow investmentInfow: investmentInfowList){
            InvestmentInfowResponseDTO responseDTO = new InvestmentInfowResponseDTO();
            BeanUtils.copyProperties(investmentInfow, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        investmentInfowRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public void add(InvestmentInfowRequestDTO requestDTO){

        InvestmentInfow investmentInfow = new InvestmentInfow();
        BeanUtils.copyProperties(requestDTO, investmentInfow);
        investmentInfowRepository.save(investmentInfow);
    }
}
