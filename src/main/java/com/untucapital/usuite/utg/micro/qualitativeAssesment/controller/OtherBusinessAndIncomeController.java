package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.OtherBusinessAndIncomeService;
import com.untucapital.usuite.utg.model.OtherBusinessAndIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "otherBusinessAndIncome")
public class OtherBusinessAndIncomeController {
    @Autowired
    private final OtherBusinessAndIncomeService otherBusinessAndIncomeService;

    public OtherBusinessAndIncomeController(OtherBusinessAndIncomeService otherBusinessAndIncomeService) {
        this.otherBusinessAndIncomeService = otherBusinessAndIncomeService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody OtherBusinessAndIncome otherBusinessAndIncome){
        otherBusinessAndIncomeService.save(otherBusinessAndIncome);
    }
    //Delete By Id
    @DeleteMapping("/delete/{otherBusinessAndIncomeId}")
    public void deleteById(@PathVariable("otherBusinessAndIncomeId") String id){
        otherBusinessAndIncomeService.deleteById(id);
    }
    //Find All By Loan Id
    @GetMapping("/get/{loanId}")
    public List<OtherBusinessAndIncome> findAllByLoan(@PathVariable("loanId") String id){
        return otherBusinessAndIncomeService.findAllByLoanId(id);
    }
}
