package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.LongTermCreditHistoryRequestDTO;
import com.untucapital.usuite.utg.dto.response.LongTermCreditHistoryResponseDTO;
import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.repository.LongTermCreditHistoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LongTermCreditHistoryService {
    @Autowired
    LongTermCreditHistoryRepository longTermCreditHistoryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCreditHistory(LongTermCreditHistoryRequestDTO request){

        LongTermCreditHistory longTermCreditHistory = new LongTermCreditHistory();
        BeanUtils.copyProperties(request, longTermCreditHistory);
        longTermCreditHistoryRepository.save(longTermCreditHistory);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LongTermCreditHistoryResponseDTO> getCreditHistoryByLoanId(String loanId){

        List<LongTermCreditHistoryResponseDTO> response = new ArrayList<LongTermCreditHistoryResponseDTO>();
        List<LongTermCreditHistory> longTermCreditHistoryList= longTermCreditHistoryRepository.findByLoanId(loanId);

        for(LongTermCreditHistory longTermCreditHistory : longTermCreditHistoryList){

            LongTermCreditHistoryResponseDTO responseDTO = new LongTermCreditHistoryResponseDTO();
            BeanUtils.copyProperties(longTermCreditHistory,responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCreditHistory(String id){
        longTermCreditHistoryRepository.deleteById(id);
    }
}
