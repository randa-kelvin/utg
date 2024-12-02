package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CurrentAssetsMicroService;
import com.untucapital.usuite.utg.model.CurrentAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("currentAssetsMicro")
public class CurrentAssetsMicroController {
    @Autowired
    private final CurrentAssetsMicroService currentAssetsMicroService;

    public CurrentAssetsMicroController(CurrentAssetsMicroService currentAssetsMicroService) {
        this.currentAssetsMicroService = currentAssetsMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody CurrentAssetsMicro currentAssetsMicro){
        currentAssetsMicroService.save(currentAssetsMicro);
    }
    //Delete loan id
    @DeleteMapping("/delete/{currentAssetsMicroId}")
    public void deleteByLoanId(@PathVariable("currentAssetsMicroId") String id){
        currentAssetsMicroService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<CurrentAssetsMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return currentAssetsMicroService.findAllByLoanId(id);
    }
}
