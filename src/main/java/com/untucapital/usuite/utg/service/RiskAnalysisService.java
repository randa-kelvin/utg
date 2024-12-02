package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.RiskAnalysisRequestDTO;
import com.untucapital.usuite.utg.dto.response.RiskAnalysisResponseDTO;
import com.untucapital.usuite.utg.model.RiskAnalysis;
import com.untucapital.usuite.utg.repository.RiskAnalysisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class RiskAnalysisService {
    @Autowired
    RiskAnalysisRepository riskAnalysisRepository;

    @Transactional(value = "transactionManager")
    public void saveRiskAnalysis(RiskAnalysisRequestDTO request){

        RiskAnalysis riskAnalysis = new RiskAnalysis();
        BeanUtils.copyProperties(request, riskAnalysis);
        riskAnalysisRepository.save(riskAnalysis);
    }

    @Transactional(value = "transactionManager")
    public List<RiskAnalysisResponseDTO> getRiskAnalysisByLoanId(String loanId){

        List<RiskAnalysisResponseDTO> responseDTOs = new ArrayList<>();
        List<RiskAnalysis> riskAnalysisList= riskAnalysisRepository.findByLoanId(loanId);

        for (RiskAnalysis riskAnalysis : riskAnalysisList) {
            RiskAnalysisResponseDTO responseDTO = new RiskAnalysisResponseDTO();
            BeanUtils.copyProperties(riskAnalysis, responseDTO);
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    @Transactional(value = "transactionManager")
    public void deleteRiskAnalysis(String id){
        riskAnalysisRepository.deleteById(id);
    }
}


