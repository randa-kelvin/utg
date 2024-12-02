package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.LongTermLiabilityRequestDTO;
import com.untucapital.usuite.utg.dto.response.LongTermLiabilityResponseDTO;
import com.untucapital.usuite.utg.model.LongTermLiability;
import com.untucapital.usuite.utg.repository.LongTermLiabilityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class LongTermLiabilityService {
    @Autowired
    private final LongTermLiabilityRepository longTermLiabilityRepository;


    public LongTermLiabilityService(LongTermLiabilityRepository longTermLiabilityRepository) {
        this.longTermLiabilityRepository = longTermLiabilityRepository;
    }

    @Transactional(value = "transactionManager")
    public void saveLiability(LongTermLiabilityRequestDTO request){

        LongTermLiability longTermLiability = new LongTermLiability();
        longTermLiabilityRepository.save(longTermLiability);
    }

    @Transactional(value = "transactionManager")
    public List<LongTermLiabilityResponseDTO> getLiability(String loanId){

        List<LongTermLiabilityResponseDTO> response = new ArrayList<LongTermLiabilityResponseDTO>();
        List<LongTermLiability> longTermLiabilityList = longTermLiabilityRepository.findLongTermLiabilityByLoanId(loanId);

        for(LongTermLiability longTermLiability : longTermLiabilityList){

            LongTermLiabilityResponseDTO longTermLiabilityResponse = new LongTermLiabilityResponseDTO();
            BeanUtils.copyProperties(longTermLiability,longTermLiabilityResponse);

            response.add(longTermLiabilityResponse);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteLiability(String id){
        longTermLiabilityRepository.deleteById(id);
    }

}
