package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.ApplicationOfFundsService;
import com.untucapital.usuite.utg.model.ApplicationOfFunds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "applicationOfFunds")
public class ApplicationOfFundsController {
    @Autowired
    private final ApplicationOfFundsService applicationOfFundsService;

    public ApplicationOfFundsController(ApplicationOfFundsService applicationOfFundsService) {
        this.applicationOfFundsService = applicationOfFundsService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody ApplicationOfFunds applicationOfFunds){
        applicationOfFundsService.save(applicationOfFunds);
    }
    //Delete By id
    @DeleteMapping("/delete/{applicationOfFundId}")
    public void deleteById(@PathVariable("applicationOfFundId") String id){
        applicationOfFundsService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<ApplicationOfFunds> findAllLoanId(@PathVariable("loanId") String id){
        return applicationOfFundsService.findAllByLoanId(id);
    }
}
