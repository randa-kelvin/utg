package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.GuarantorService;
import com.untucapital.usuite.utg.model.Guarantor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "guarantor")
public class GuarantorController {
    @Autowired
    private final GuarantorService guarantorService;

    public GuarantorController(GuarantorService guarantorService) {
        this.guarantorService = guarantorService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody Guarantor guarantor){
        guarantorService.save(guarantor);
    }
    //Delete by id
    @DeleteMapping("/delete/{guarantorId}")
    public void deleteById(@PathVariable("guarantorId") String id){
        guarantorService.deleteById(id);
    }
    //Find all by id
    @GetMapping("/get/{loanId}")
    public List<Guarantor> findAllByLoanId(@PathVariable("loanId") String id){
        return guarantorService.findAllByLoanId(id);
    }

}
