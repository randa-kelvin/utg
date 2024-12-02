package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.InvestmentInfowRequestDTO;
import com.untucapital.usuite.utg.dto.response.InvestmentInfowResponseDTO;
import com.untucapital.usuite.utg.service.InvestmentInflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investmnetInflow")
public class InvestmentInflowController {
    @Autowired
    InvestmentInflowService investmentInflowService;

    private static final Logger log = LoggerFactory.getLogger(InvestmentInflowController.class);

    @GetMapping("/get/{loanId}")
    public List<InvestmentInfowResponseDTO> get(@PathVariable("loanId") String loanId){
       return investmentInflowService.findByLoanId(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody InvestmentInfowRequestDTO investmentInfow){
        investmentInflowService.add(investmentInfow);
    }

    @DeleteMapping("delete/{id}")
    public  void delete(@PathVariable String id){
        investmentInflowService.delete(id);
    }

}
