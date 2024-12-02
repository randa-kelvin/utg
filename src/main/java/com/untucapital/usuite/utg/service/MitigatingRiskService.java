package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MitigatingRiskRequestDTO;
import com.untucapital.usuite.utg.dto.response.MitigatingRiskResponseDTO;
import com.untucapital.usuite.utg.model.MitigatingRisk;
import com.untucapital.usuite.utg.repository.MitigatingRiskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class MitigatingRiskService {
    @Autowired
    MitigatingRiskRepository mitigatingRiskRepository;

    @Transactional(value = "transactionManager")
    public void saveMitigatingRisk(MitigatingRiskRequestDTO requestDTO){

        MitigatingRisk mitigatingRisk = new MitigatingRisk();
        BeanUtils.copyProperties(requestDTO,mitigatingRisk);
        mitigatingRiskRepository.save(mitigatingRisk);
    }

    @Transactional(value = "transactionManager")
    public List<MitigatingRiskResponseDTO> getMitigatingRiskByLoanId(String loanId){

        List<MitigatingRiskResponseDTO> result = new ArrayList<>();
        List<MitigatingRisk> mitigatingRiskList = mitigatingRiskRepository.findByLoanId(loanId);

        for(MitigatingRisk mitigatingRisk : mitigatingRiskList){

            MitigatingRiskResponseDTO responseDTO = new MitigatingRiskResponseDTO();
            BeanUtils.copyProperties(mitigatingRisk, responseDTO);

            result.add(responseDTO);
        }

        return result;
    }

    @Transactional(value = "transactionManager")
    public void deleteMitigatingRisk(String id){
        mitigatingRiskRepository.deleteById(id);
    }
}

