package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.MainProductPurchasesMicroService;
import com.untucapital.usuite.utg.model.MainProductPurchasesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mainProductPurchases")
public class MainProductPurchasesMicroController {
    @Autowired
    private final MainProductPurchasesMicroService  mainProductPurchasesMicroService;

    public MainProductPurchasesMicroController(MainProductPurchasesMicroService mainProductPurchasesMicroService) {
        this.mainProductPurchasesMicroService = mainProductPurchasesMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody MainProductPurchasesMicro mainProductPurchasesMicro){
        mainProductPurchasesMicroService.save(mainProductPurchasesMicro);
    }
    //Delete
    @DeleteMapping("/delete/{mainProductPurchasesId}")
    public void deleteById(@PathVariable("mainProductPurchasesId") String id){
        mainProductPurchasesMicroService.deleteById(id);
    }
    //Find all by loan
    @GetMapping("/get/{loanId}")
    public List<MainProductPurchasesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return mainProductPurchasesMicroService.findAllLoanId(id);
    }
}
