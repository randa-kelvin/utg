package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CashOnHandMicroService;
import com.untucapital.usuite.utg.model.CashOnHandMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cashOnHand")
public class CashOnHandMicroController {
    @Autowired
    private final CashOnHandMicroService cashOnHandMicroService;

    public CashOnHandMicroController(CashOnHandMicroService cashOnHandMicroService) {
        this.cashOnHandMicroService = cashOnHandMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody CashOnHandMicro cashOnHandMicro){
        cashOnHandMicroService.save(cashOnHandMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{cashOnHandId}")
    public void deleteById(@PathVariable("cashOnHandId") String id){
        cashOnHandMicroService.deleteById(id);
    }
    //Find all by load
    @GetMapping("/get/{loanId}")
    public List<CashOnHandMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return cashOnHandMicroService.findAllLoanId(id);
    }
}
