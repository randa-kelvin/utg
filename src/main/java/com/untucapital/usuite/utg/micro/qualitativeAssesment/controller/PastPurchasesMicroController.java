package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.PastPurchasesMicroService;
import com.untucapital.usuite.utg.model.PastPurchasesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pastPurchases")
public class PastPurchasesMicroController {
    @Autowired
    private final PastPurchasesMicroService pastPurchasesMicroService;

    public PastPurchasesMicroController(PastPurchasesMicroService pastPurchasesMicroService) {
        this.pastPurchasesMicroService = pastPurchasesMicroService;
    }


    //Add
    @PostMapping("/save")
    public void save(@RequestBody PastPurchasesMicro pastPurchasesMicro){
        pastPurchasesMicroService.save(pastPurchasesMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{pastPurchasesId}")
    public void deleteById(@PathVariable("pastPurchasesId") String id){
        pastPurchasesMicroService.deleteById(id);
    }
    //Find all loan id
    @GetMapping("/get/{loanId}")
    public List<PastPurchasesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return pastPurchasesMicroService.findAllByLoanId(id);
    }
}
