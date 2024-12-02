package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.GuarantorAssessmentDetailsMicroService;
import com.untucapital.usuite.utg.model.GuarantorAssessmentDetailsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guarantorAssessmentDetails")
public class GuarantorAssessmentDetailsMicroController {
    @Autowired
    private final GuarantorAssessmentDetailsMicroService guarantorAssessmentDetailsMicroService;

    public GuarantorAssessmentDetailsMicroController(GuarantorAssessmentDetailsMicroService guarantorAssessmentDetailsMicroService) {
        this.guarantorAssessmentDetailsMicroService = guarantorAssessmentDetailsMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody GuarantorAssessmentDetailsMicro guarantorAssessmentDetailsMicro){
        guarantorAssessmentDetailsMicroService.save(guarantorAssessmentDetailsMicro);

    }
    //delete by id
    @DeleteMapping("/delete/{guarantorAssessmentDetailsId}")
    public void deleteById(@PathVariable("guarantorAssessmentDetailsId") String id){
        guarantorAssessmentDetailsMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<GuarantorAssessmentDetailsMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return guarantorAssessmentDetailsMicroService.findAllByLoanId(id);
    }
}
