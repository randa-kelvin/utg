package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.HouseHoldMicroService;
import com.untucapital.usuite.utg.model.HouseHoldAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("houseHoldAssets")
public class HouseHoldAssetsMicroController {
    @Autowired
    private final HouseHoldMicroService houseHoldMicroService;

    public HouseHoldAssetsMicroController(HouseHoldMicroService houseHoldMicroService) {
        this.houseHoldMicroService = houseHoldMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody HouseHoldAssetsMicro houseHoldAssetsMicro){
        houseHoldMicroService.save(houseHoldAssetsMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{houseHoldAssetsId}")
    public void deleteById(@PathVariable("houseHoldAssetsId") String id){
        houseHoldMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<HouseHoldAssetsMicro> findAllByLoadId(@PathVariable("loanId") String id){
        return houseHoldMicroService.findAllByLoanId(id);
    }
}
