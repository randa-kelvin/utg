package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.RiskAnalysisRequestDTO;
import com.untucapital.usuite.utg.dto.response.RiskAnalysisResponseDTO;
import com.untucapital.usuite.utg.service.RiskAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("risk_analysis")
public class RiskAnalysisController {
    @Autowired
    RiskAnalysisService riskAnalysisService;

    private static final Logger log = LoggerFactory.getLogger(RiskAnalysisController.class);

    @GetMapping("/get/{loanId}")
    public List<RiskAnalysisResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return riskAnalysisService.getRiskAnalysisByLoanId(loanId);
    }

    @PostMapping("/save")
    public void add(@RequestBody RiskAnalysisRequestDTO riskAnalysis) {
        riskAnalysisService.saveRiskAnalysis(riskAnalysis);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        riskAnalysisService.deleteRiskAnalysis(id);
    }


}
