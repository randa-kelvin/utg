package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.InvestmentOutflowRequestDTO;
import com.untucapital.usuite.utg.dto.response.InvestmentOutflowResponseDTO;
import com.untucapital.usuite.utg.service.InvestmentOutflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investmentOutflow")
public class InvestmentOutflowController {
    @Autowired
    InvestmentOutflowService investmentOutflowService;

    private static final Logger log = LoggerFactory.getLogger(InvestmentOutflowController.class);

    @GetMapping("/get/{loanId}")
    public List<InvestmentOutflowResponseDTO> get(@PathVariable("loanId") String loanId){
        return investmentOutflowService.get(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody InvestmentOutflowRequestDTO investmentOutflow){
        investmentOutflowService.add(investmentOutflow);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") String id){
        investmentOutflowService.delete(id);
    }

}
