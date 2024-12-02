package com.untucapital.usuite.utg.processor;


import com.untucapital.usuite.utg.dto.LoTotalPipelineAndDisbursementsDTO;
import com.untucapital.usuite.utg.dto.LoanOfficerProductivityDTO;
import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoanPipelineMapper {

    public static List<LoansPipelineDTO> mapToDTO(List<Object[]> resultList) {
        List<LoansPipelineDTO> dtos = new ArrayList<>();
        for (Object[] row : resultList) {
            LoansPipelineDTO dto = new LoansPipelineDTO();
            dto.setBranchName((String) row[0]);
            dto.setCaseloads((BigInteger) row[1]);
            dto.setDisbursements((Double) row[2]);
            dto.setPendingDisbursements((Double) row[3]);
            dto.setProspects((Double) row[4]);
            dto.setAssessments((Double) row[5]);
            dto.setTotalPipeline((Double) row[6]);
            dto.setNewClients((BigDecimal) row[7]);
            dto.setRepeatClients((BigDecimal) row[8]);
            dtos.add(dto);
        }
        return dtos;
    }

    public static List<LoanOfficerProductivityDTO> mapToLoanOfficerProductivityDTO(List<Object[]> resultList) {
        List<LoanOfficerProductivityDTO> dtos = new ArrayList<>();
        for (Object[] row : resultList) {
            LoanOfficerProductivityDTO dto = new LoanOfficerProductivityDTO();
            dto.setBranchName((String) row[0]);
            dto.setLoanOfficer((String) row[1]);
            dto.setDisbursed((BigDecimal) row[2]);
            dto.setPipelineCases((BigDecimal) row[3]);
            dto.setTotal((BigInteger) row[4]);
            dto.setAverageTarget((Double) row[5]);
            dto.setVariance((Double) row[6]);
            dtos.add(dto);
        }
        return dtos;
    }

    public static List<LoTotalPipelineAndDisbursementsDTO> mapTogetLoTotalPipelineAndDisbursements(List<Object[]> resultList) {
        List<LoTotalPipelineAndDisbursementsDTO> dtos = new ArrayList<>();
        for (Object[] row : resultList) {
            LoTotalPipelineAndDisbursementsDTO dto = new LoTotalPipelineAndDisbursementsDTO();
            dto.setBranchName((String) row[0]);
            dto.setLoanOfficer((String) row[1]);
            dto.setTotalPipeline((Double) row[2]);
            dto.setTotalDisbursed((Double) row[3]);;
            dtos.add(dto);
        }
        return dtos;
    }

}
