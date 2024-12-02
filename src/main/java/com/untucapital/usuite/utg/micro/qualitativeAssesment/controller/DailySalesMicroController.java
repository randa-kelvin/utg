package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.DailySalesMicroService;
import com.untucapital.usuite.utg.model.DailySalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dailySales")
public class DailySalesMicroController {
    @Autowired
    private final DailySalesMicroService dailySalesMicroService;

    public DailySalesMicroController(DailySalesMicroService dailySalesMicroService) {
        this.dailySalesMicroService = dailySalesMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody DailySalesMicro dailySalesMicro){
        dailySalesMicroService.save(dailySalesMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{dailySalesId}")
    public void deleteById(@PathVariable("dailySalesId") String id){
        dailySalesMicroService.deleteById(id);
    }
    //Find all loan id
    @GetMapping("/get/{loanId}")
    public List<DailySalesMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return dailySalesMicroService.findAllByLoanId(id);
    }
}
