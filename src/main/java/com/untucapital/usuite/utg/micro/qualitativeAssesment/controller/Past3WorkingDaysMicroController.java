package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.Past3WorkingDaysMicroService;
import com.untucapital.usuite.utg.model.Past3WorkingDaysMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("past3WorkingDays")
public class Past3WorkingDaysMicroController {
    @Autowired
    private final Past3WorkingDaysMicroService past3WorkingDaysMicroService;

    public Past3WorkingDaysMicroController(Past3WorkingDaysMicroService past3WorkingDaysMicroService) {
        this.past3WorkingDaysMicroService = past3WorkingDaysMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody Past3WorkingDaysMicro past3WorkingDaysMicro){
        past3WorkingDaysMicroService.save(past3WorkingDaysMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{past3WorkingDaysId}")
    public void deleteById(@PathVariable("past3WorkingDaysId") String id){
        past3WorkingDaysMicroService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<Past3WorkingDaysMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return past3WorkingDaysMicroService.findAllByLoanId(id);
    }
}
