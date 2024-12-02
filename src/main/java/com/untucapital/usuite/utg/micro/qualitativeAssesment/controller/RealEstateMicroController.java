package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.RealEstateMicroService;
import com.untucapital.usuite.utg.model.RealEstateMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("realEstate")
public class RealEstateMicroController {
    @Autowired
    private final RealEstateMicroService realEstateMicroService;

    public RealEstateMicroController(RealEstateMicroService realEstateMicroService) {
        this.realEstateMicroService = realEstateMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody RealEstateMicro realEstateMicro){
        realEstateMicroService.save(realEstateMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{realEstateId}")
    public void deleteById(@PathVariable("realEstateId") String id){
        realEstateMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<RealEstateMicro> findAllByLoadId(@PathVariable("loanId") String id){
        return  realEstateMicroService.findAllByLoanId(id);
    }
}
