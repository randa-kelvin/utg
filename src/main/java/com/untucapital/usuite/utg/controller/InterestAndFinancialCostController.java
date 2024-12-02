package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.InterestAndFinancialCostRequestDTO;
import com.untucapital.usuite.utg.dto.response.InterestAndFinancialCostResponseDTO;
import com.untucapital.usuite.utg.service.InterestAndFinancialCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("interestAndFinancialCost")
public class InterestAndFinancialCostController {
    @Autowired
    InterestAndFinancialCostService interestAndFinancialCostService;

    private static final Logger log = LoggerFactory.getLogger(InterestAndFinancialCostController.class);


    @PostMapping("/add")
    public void add(@RequestBody InterestAndFinancialCostRequestDTO interestAndFinancialCost) {
        interestAndFinancialCostService.save(interestAndFinancialCost);
    }

    @GetMapping("/get/{loanId}")
    public List<InterestAndFinancialCostResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return interestAndFinancialCostService.findByLoanId(loanId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(String id){
        interestAndFinancialCostService.delete(id);
    }
}
