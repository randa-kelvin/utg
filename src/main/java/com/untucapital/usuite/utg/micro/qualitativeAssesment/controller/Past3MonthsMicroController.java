package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.Past3MonthsMicroService;
import com.untucapital.usuite.utg.model.Past3MonthsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("past3Months")
public class Past3MonthsMicroController {
    @Autowired
    private final Past3MonthsMicroService past3MonthsMicroService;

    public Past3MonthsMicroController(Past3MonthsMicroService past3MonthsMicroService) {
        this.past3MonthsMicroService = past3MonthsMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody Past3MonthsMicro past3MonthsMicro){
        past3MonthsMicroService.save(past3MonthsMicro);
    }
    //Delete by loan id
    @DeleteMapping("/delete/{past3MonthsId}")
    public void deleteById(@PathVariable("past3MonthsId") String id){
        past3MonthsMicroService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<Past3MonthsMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return past3MonthsMicroService.findAllByLoanId(id);
    }
}
