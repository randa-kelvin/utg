package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.AverageDailySalesMicroService;
import com.untucapital.usuite.utg.model.AverageDailySalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("averageDailySales")
public class AverageDailySalesMicroController {
    @Autowired
    private final AverageDailySalesMicroService averageDailySalesMicroService;

    public AverageDailySalesMicroController(AverageDailySalesMicroService averageDailySalesMicroService) {
        this.averageDailySalesMicroService = averageDailySalesMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody AverageDailySalesMicro averageDailySalesMicro){
        averageDailySalesMicroService.save(averageDailySalesMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{averageDailySalesId}")
    public void deleteById(@PathVariable("averageDailySalesId") String id){
        averageDailySalesMicroService.deleteById(id);
    }
    //Find all loan id
    @GetMapping("/get/{loanId}")
    public List<AverageDailySalesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return averageDailySalesMicroService.findAllByLoanId(id);
    }
}
