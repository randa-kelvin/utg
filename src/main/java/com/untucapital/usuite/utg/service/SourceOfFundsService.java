package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.SourceOfFundsRequestDTO;
import com.untucapital.usuite.utg.dto.response.SourceOfFundsResponseDTO;
import com.untucapital.usuite.utg.model.SourceOfFunds;
import com.untucapital.usuite.utg.repository.SourceOfFundsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class SourceOfFundsService {
    @Autowired
    SourceOfFundsRepository sourceOfFundsRepository;

    @Transactional(value = "transactionManager")
    public void saveSourceOfFunds(SourceOfFundsRequestDTO requestDTO){

        SourceOfFunds sourceOfFunds = new SourceOfFunds();
        BeanUtils.copyProperties(requestDTO, sourceOfFunds);
        sourceOfFundsRepository.save(sourceOfFunds);
    }

    @Transactional(value = "transactionManager")
    public List<SourceOfFundsResponseDTO> getSourceOfFundsByLoanId(String loanId){

        List<SourceOfFundsResponseDTO> result = new ArrayList<>();
        List<SourceOfFunds> sourceOfFundsList = sourceOfFundsRepository.findByLoanId(loanId);

        for (SourceOfFunds sourceOfFunds : sourceOfFundsList) {
            SourceOfFundsResponseDTO responseDTO = new SourceOfFundsResponseDTO();
            BeanUtils.copyProperties(sourceOfFunds, responseDTO);
            result.add(responseDTO);
        }

        return result;
    }

    @Transactional(value = "transactionManager")
    public void deleteSourceOfFunds(String id){
      sourceOfFundsRepository.deleteById(id);
    }
}
