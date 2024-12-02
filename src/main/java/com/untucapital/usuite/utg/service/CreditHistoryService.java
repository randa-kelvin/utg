package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.CreditHistoryRequestDTO;
import com.untucapital.usuite.utg.dto.response.CreditHistoryResponseDTO;
import com.untucapital.usuite.utg.model.CreditHistory;
import com.untucapital.usuite.utg.repository.CreditHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CreditHistoryService {
    @Autowired
    CreditHistoryRepository creditHistoryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCreditHistory(CreditHistoryRequestDTO request){

        CreditHistory creditHistory = new CreditHistory();
        BeanUtils.copyProperties(request, creditHistory);
        creditHistoryRepository.save(creditHistory);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CreditHistoryResponseDTO> getCreditHistoryByLoanId(String loanId){

        List<CreditHistoryResponseDTO> response = new ArrayList<CreditHistoryResponseDTO>();
        List<CreditHistory> creditHistoryList = creditHistoryRepository.findByLoanId(loanId);

        for(CreditHistory creditHistory: creditHistoryList){
            CreditHistoryResponseDTO responseDTO = new CreditHistoryResponseDTO();
            BeanUtils .copyProperties(creditHistory, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCreditHistory(String id){
        creditHistoryRepository.deleteById(id);
    }

}
