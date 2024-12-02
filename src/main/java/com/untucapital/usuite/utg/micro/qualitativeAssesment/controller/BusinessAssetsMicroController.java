package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.BusinessAssetsMicroService;
import com.untucapital.usuite.utg.model.BusinessAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("businessAssets")
public class BusinessAssetsMicroController {
    @Autowired
    private final BusinessAssetsMicroService businessAssetsMicroService;

    public BusinessAssetsMicroController(BusinessAssetsMicroService businessAssetsMicroService) {
        this.businessAssetsMicroService = businessAssetsMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody BusinessAssetsMicro businessAssetsMicro){
        businessAssetsMicroService.save(businessAssetsMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{businessAssetsId}")
    public void deleteById(@PathVariable("businessAssetsId") String id){
        businessAssetsMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<BusinessAssetsMicro> findAllByLoadId(@PathVariable("loanId") String id){
        return businessAssetsMicroService.findAllByLoanId(id);
    }
}
