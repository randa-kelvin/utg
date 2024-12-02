package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.PeriodOfCostAnalysisRequestDTO;
import com.untucapital.usuite.utg.dto.response.PeriodOfCostAnalysisResponseDTO;
import com.untucapital.usuite.utg.model.PeriodOfCostAnalysis;
import com.untucapital.usuite.utg.repository.PeriodOfCostAnalysisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@javax.transaction.Transactional
@Service
public class PeriodOfCostAnalysisService {
    @Autowired
    private PeriodOfCostAnalysisRepository periodOfCostAnalysisRepository;

    @Transactional(value = "transactionManager")
    public List<PeriodOfCostAnalysisResponseDTO> getPeriod(String loanId) {

        List<PeriodOfCostAnalysisResponseDTO> response = new ArrayList<>();
        List<PeriodOfCostAnalysis>  periodOfCostAnalysisList =periodOfCostAnalysisRepository.findByLoanId(loanId);

        for (PeriodOfCostAnalysis periodOfCostAnalysis : periodOfCostAnalysisList) {
            PeriodOfCostAnalysisResponseDTO responseDTO = new PeriodOfCostAnalysisResponseDTO();
            BeanUtils.copyProperties(periodOfCostAnalysis, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void  savePeriod(PeriodOfCostAnalysisRequestDTO request) {

        PeriodOfCostAnalysis periodOfCostAnalysis = new PeriodOfCostAnalysis();
        BeanUtils.copyProperties(request, periodOfCostAnalysis);
        if(periodOfCostAnalysisRepository.existsById(periodOfCostAnalysis.getId())){

        }else{
            periodOfCostAnalysisRepository.save(periodOfCostAnalysis);
        }
    }

    @Transactional(value = "transactionManager")
    public List<PeriodOfCostAnalysisResponseDTO> getAll() {

        List<PeriodOfCostAnalysisResponseDTO> response = new ArrayList<PeriodOfCostAnalysisResponseDTO>();
        List<PeriodOfCostAnalysis> periodOfCostAnalysisList = periodOfCostAnalysisRepository.findAll();

        for (PeriodOfCostAnalysis periodOfCostAnalysis : periodOfCostAnalysisList) {
            PeriodOfCostAnalysisResponseDTO responseDTO = new PeriodOfCostAnalysisResponseDTO();
            BeanUtils.copyProperties(periodOfCostAnalysis, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }
}
