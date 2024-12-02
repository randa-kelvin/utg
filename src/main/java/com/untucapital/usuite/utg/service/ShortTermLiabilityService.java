package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.ShortTermLiabilityRequestDTO;
import com.untucapital.usuite.utg.dto.response.ShortTermLiabilityResponseDTO;
import com.untucapital.usuite.utg.model.ShortTermLiability;
import com.untucapital.usuite.utg.repository.ShortTermLiabilityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class ShortTermLiabilityService {
    @Autowired
    ShortTermLiabilityRepository shortTermLiabilityRepository;

    @Transactional(value = "transactionManager")
    public void saveLiability(ShortTermLiabilityRequestDTO request){

        ShortTermLiability shortTermLiability = new ShortTermLiability();
        BeanUtils.copyProperties(request, shortTermLiability);
        shortTermLiabilityRepository.save(shortTermLiability);
    }

    @Transactional(value = "transactionManager")
    public List<ShortTermLiabilityResponseDTO> getLiability(String loanId){

        List<ShortTermLiabilityResponseDTO> response = new ArrayList<>();
        List<ShortTermLiability> shortTermLiabilityList = shortTermLiabilityRepository.findShortTermLiabilityByLoanId(loanId);

        for (ShortTermLiability shortTermLiability : shortTermLiabilityList) {
            ShortTermLiabilityResponseDTO responseDTO = new ShortTermLiabilityResponseDTO();
            BeanUtils.copyProperties(shortTermLiability, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteLiablity(String id){
        shortTermLiabilityRepository.deleteById(id);
    }
}
