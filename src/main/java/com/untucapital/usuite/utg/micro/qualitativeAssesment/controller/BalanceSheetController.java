package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.BalanceSheetService;
import com.untucapital.usuite.utg.model.BalanceSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "balanceSheet")
public class BalanceSheetController {
    @Autowired
    private final BalanceSheetService balanceSheetService;

    public BalanceSheetController(BalanceSheetService balanceSheetService) {
        this.balanceSheetService = balanceSheetService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody BalanceSheet balanceSheet){
        balanceSheetService.save(balanceSheet);
    }
    //Delete by id
    @DeleteMapping("/delete/{balanceSheetId}")
    public void deleteById(@PathVariable("balanceSheetId") String id){
        balanceSheetService.deleteByLoanId(id);
    }
    //FInd all by loan id
    @GetMapping("/get/{loanId}")
    public List<BalanceSheet> findAllByLoanId(@PathVariable("loanId") String id){
        return balanceSheetService.findAllByLoanId(id);
    }
}
