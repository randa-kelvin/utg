package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CapacityAssessmentMicroService;
import com.untucapital.usuite.utg.model.CapacityAssessmentMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("capacityAssessment")
public class CapacityAssessmentMicroController {
    @Autowired
    private final CapacityAssessmentMicroService capacityAssessmentMicroService;

    public CapacityAssessmentMicroController(CapacityAssessmentMicroService capacityAssessmentMicroService) {
        this.capacityAssessmentMicroService = capacityAssessmentMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody CapacityAssessmentMicro capacityAssessmentMicro){
        capacityAssessmentMicroService.save(capacityAssessmentMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{capacityAssessmentId}")
    public void deleteById(@PathVariable("capacityAssessmentId") String id){
        capacityAssessmentMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<CapacityAssessmentMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return capacityAssessmentMicroService.findAllByLoanId(id);
    }
}
