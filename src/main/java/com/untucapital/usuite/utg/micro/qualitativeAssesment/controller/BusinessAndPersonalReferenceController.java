package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.BusinessAndPersonalReferenceService;
import com.untucapital.usuite.utg.model.BusinessAndPersonalReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "businessAndPersonalReference")
public class BusinessAndPersonalReferenceController {
    @Autowired
    private final BusinessAndPersonalReferenceService businessAndPersonalReferenceService;

    public BusinessAndPersonalReferenceController(BusinessAndPersonalReferenceService businessAndPersonalReferenceService) {
        this.businessAndPersonalReferenceService = businessAndPersonalReferenceService;
    }

    //Save
    @PostMapping("/save")
    public void save(@RequestBody BusinessAndPersonalReference businessAndPersonalReference){
        businessAndPersonalReferenceService.save(businessAndPersonalReference);
    }
    //Delete By Id
    @DeleteMapping("delete/{generalBusinessReferenceId}")
    public void deleteById(@PathVariable("generalBusinessReferenceId") String id){
        businessAndPersonalReferenceService.delete(id);
    }
    //Find All By LoanId
    @GetMapping("/get/{loanId}")
    public List<BusinessAndPersonalReference> findAllByLoanId(@PathVariable("loanId") String id){
        return businessAndPersonalReferenceService.findAllByLoanId(id);
    }
}
