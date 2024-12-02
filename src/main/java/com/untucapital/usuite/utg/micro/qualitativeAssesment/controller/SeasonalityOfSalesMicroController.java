package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.SeasonalityOfSalesMicroService;
import com.untucapital.usuite.utg.model.SeasonalityOfSalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seasonalityOfSales")
public class SeasonalityOfSalesMicroController {
    @Autowired
    private final SeasonalityOfSalesMicroService seasonalityOfSalesMicroService;

    public SeasonalityOfSalesMicroController(SeasonalityOfSalesMicroService seasonalityOfSalesMicroService) {
        this.seasonalityOfSalesMicroService = seasonalityOfSalesMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody SeasonalityOfSalesMicro seasonalityOfSalesMicro){
        seasonalityOfSalesMicroService.save(seasonalityOfSalesMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{seasonalityOfSalesId}")
    public void deleteById(@PathVariable("seasonalityOfSalesId") String id){
        seasonalityOfSalesMicroService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<SeasonalityOfSalesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return  seasonalityOfSalesMicroService.findAllByLoanId(id);
    }
}
