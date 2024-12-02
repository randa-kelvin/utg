package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.SwotRiskAnalysisService;
import com.untucapital.usuite.utg.model.SwotRiskAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "swotRiskAnalysis")
public class SwotRiskAnalysisController {
    @Autowired
    private final SwotRiskAnalysisService swotRiskAnalysisService;

    public SwotRiskAnalysisController(SwotRiskAnalysisService swotRiskAnalysisService) {
        this.swotRiskAnalysisService = swotRiskAnalysisService;
    }
    //Save
    @PostMapping("/save")
    public void save(@RequestBody SwotRiskAnalysis swotRiskAnalysis){
        swotRiskAnalysisService.save(swotRiskAnalysis);
    }
    //Delete by Id
    @DeleteMapping("/delete/{swotAnalysisId}")
    public void deleteById(@PathVariable("swotRiskAnalysisId") String id){
        swotRiskAnalysisService.deleteById(id);
    }
    //Find All By Loan Id
    @GetMapping("/get/{loanId}")
    public List<SwotRiskAnalysis> findAllById(@PathVariable("loanId") String id){
        return swotRiskAnalysisService.findAllByLoanId(id);
    }
}
