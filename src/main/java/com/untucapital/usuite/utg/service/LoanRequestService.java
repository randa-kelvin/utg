package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.LoanRequestRequestDTO;
import com.untucapital.usuite.utg.dto.response.LoanRequestResponseDTO;
import com.untucapital.usuite.utg.model.LoanRequest;
import com.untucapital.usuite.utg.repository.LoanRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class LoanRequestService {
    @Autowired
    LoanRequestRepository loanRequestRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveLoanRequest(LoanRequestRequestDTO request){

        LoanRequest loanRequest = new LoanRequest();
        BeanUtils.copyProperties(request, loanRequest);
        loanRequestRepository.save(loanRequest);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<LoanRequestResponseDTO> getByLoanId(String loanId){

        List<LoanRequestResponseDTO> result = new ArrayList<LoanRequestResponseDTO>();
        List<LoanRequest> loanRequestList = loanRequestRepository.findLoanRequestByLoanId(loanId);

        for(LoanRequest loanRequest : loanRequestList){

            LoanRequestResponseDTO responseDTO = new LoanRequestResponseDTO();
            BeanUtils.copyProperties(loanRequest, responseDTO);

            result.add(responseDTO);
        }

        return result;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteLoanRequest(String id){
        loanRequestRepository.deleteById(id);
    }
}
