package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.FixedAssetsServiceMicro;
import com.untucapital.usuite.utg.model.FixedAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "fixedAssetsMicro")
public class FixedAssetsControllerMicro {
    @Autowired
    FixedAssetsServiceMicro fixedAssetsServiceMicro;

    //Add
    @PostMapping("/save")
    public void save(@RequestBody FixedAssetsMicro fixedAssets){
        fixedAssetsServiceMicro.save(fixedAssets);
    }
    //Delete by id
    @DeleteMapping("/delete/{fixedAssetsId}")
    public void deleteById(@PathVariable("fixedAssetsId") String id) {
        fixedAssetsServiceMicro.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("get/{loanId}")
    public List<FixedAssetsMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return fixedAssetsServiceMicro.findAllByLoanId(id);
    }
}
