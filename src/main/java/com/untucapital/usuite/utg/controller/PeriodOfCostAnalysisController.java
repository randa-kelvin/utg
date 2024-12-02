package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.PeriodOfCostAnalysisRequestDTO;
import com.untucapital.usuite.utg.dto.response.PeriodOfCostAnalysisResponseDTO;
import com.untucapital.usuite.utg.service.PeriodOfCostAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/period_of_analysis")
public class PeriodOfCostAnalysisController {

    @Autowired
    PeriodOfCostAnalysisService periodOfCostAnalysisService;

    private static final Logger log = LoggerFactory.getLogger(PeriodOfCostAnalysisController.class);

    @GetMapping("/get_period/{loanId}")
    public List<PeriodOfCostAnalysisResponseDTO> getPeriod(@PathVariable("loanId") String loanId) {
        return periodOfCostAnalysisService.getPeriod(loanId);
    }

    @PostMapping("/period")
    public void add(@RequestBody PeriodOfCostAnalysisRequestDTO periodOfCostAnalysis) {
        periodOfCostAnalysisService.savePeriod(periodOfCostAnalysis);
    }

    @GetMapping("/get_all")
    public List<PeriodOfCostAnalysisResponseDTO> getAll() {
        return periodOfCostAnalysisService.getAll();
    }



}
