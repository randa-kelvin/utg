package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CostOfSalesMicroService;
import com.untucapital.usuite.utg.model.CostOfSalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("costOfSales")
public class CostOfSalesMicroController {
    @Autowired
    private final CostOfSalesMicroService costOfSalesMicroService;

    public CostOfSalesMicroController(CostOfSalesMicroService costOfSalesMicroService) {
        this.costOfSalesMicroService = costOfSalesMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody CostOfSalesMicro costOfSalesMicro){
      costOfSalesMicroService.save(costOfSalesMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{costOfSalesId}")
    public void deleteById(@PathVariable("costOfSalesId") String id){
        costOfSalesMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<CostOfSalesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return costOfSalesMicroService.findAllByLoanId(id);
    }
}
