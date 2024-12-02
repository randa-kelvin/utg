package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.SalesPurchaseCogsGrossMarginService;
import com.untucapital.usuite.utg.model.SalesPurchaseCogsGrossMargin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "salesPurchasesCogsGrossMargin")
public class SalesPurchasesCogsMarginController {
    @Autowired
    private final SalesPurchaseCogsGrossMarginService salesPurchaseCogsGrossMarginService;

    public SalesPurchasesCogsMarginController(SalesPurchaseCogsGrossMarginService salesPurchaseCogsGrossMarginService) {
        this.salesPurchaseCogsGrossMarginService = salesPurchaseCogsGrossMarginService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody SalesPurchaseCogsGrossMargin salesPurchaseCogsGrossMargin){
        salesPurchaseCogsGrossMarginService.save(salesPurchaseCogsGrossMargin);
    }
    //Delete By id
    @DeleteMapping("/delete/{salesPurchasesCogsMarginId}")
    public void deleteById(@PathVariable("salesPurchasesCogsMarginId") String id){
        salesPurchaseCogsGrossMarginService.deleteById(id);
    }
    //Find All load id
    @GetMapping("/get/{loanId}")
    public List<SalesPurchaseCogsGrossMargin> findAllByLoanId(@PathVariable("loanId") String id){
        return salesPurchaseCogsGrossMarginService.findAllByLoanId(id);
    }

}
