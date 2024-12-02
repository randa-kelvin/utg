package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CapacityAssessment2MicroService;
import com.untucapital.usuite.utg.model.CapacityAssessment2Micro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("capacityAssessment2")
public class CapacityAssessment2MicroController {
    @Autowired
    private final CapacityAssessment2MicroService capacityAssessment2MicroService;

    public CapacityAssessment2MicroController(CapacityAssessment2MicroService capacityAssessment2MicroService) {
        this.capacityAssessment2MicroService = capacityAssessment2MicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody CapacityAssessment2Micro capacityAssessment2Micro){
        capacityAssessment2MicroService.save(capacityAssessment2Micro);
    }
    //delete by id
    @DeleteMapping("/delete/{capacityAssessment2Id}")
    public void deleteById(@PathVariable("capacityAssessment2Id") String id){
        capacityAssessment2MicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<CapacityAssessment2Micro> findAllByLoanId(@PathVariable("loanId") String id){
        return capacityAssessment2MicroService.findAllByLoanId(id);
    }
}
