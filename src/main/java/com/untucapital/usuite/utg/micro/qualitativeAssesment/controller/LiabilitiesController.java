package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.LiabilitiesService;
import com.untucapital.usuite.utg.model.Liabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "liabilities")
public class LiabilitiesController {
    @Autowired
    private final LiabilitiesService liabilitiesService;

    public LiabilitiesController(LiabilitiesService liabilitiesService) {
        this.liabilitiesService = liabilitiesService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody Liabilities liabilities){
        liabilitiesService.save(liabilities);
    }
    //Delete by id
    @DeleteMapping("/delete/{liabilityId}")
    public void deleteById(@PathVariable("liabilityId") String id){
        liabilitiesService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<Liabilities> findAllLoanId(@PathVariable("loanId") String id){
        return liabilitiesService.findAllByLoanId(id);
    }
}
