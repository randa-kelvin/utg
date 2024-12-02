package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.GeneralBusinessInformationService;
import com.untucapital.usuite.utg.model.GeneralBusinessInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "generalBusinessInformation")
public class GeneralInformationController {
    @Autowired
    private final GeneralBusinessInformationService generalBusinessInformationService;


    public GeneralInformationController(GeneralBusinessInformationService generalBusinessInformationService) {
        this.generalBusinessInformationService = generalBusinessInformationService;
    }

    //Add
    @PostMapping("/save")
    public void saveGeneral(@RequestBody GeneralBusinessInformation generalBusinessInformation){
        generalBusinessInformationService.saveGeneral(generalBusinessInformation);
    }

    //Delete By Id
    @DeleteMapping("delete/{generalBusinessInformationId}")
    public void deleteById(@PathVariable("generalBusinessInformationId") String id){
        generalBusinessInformationService.deleteGeneral(id);
    }

    //Find All By LoanId
    @GetMapping("get/{loanId}")
    public List<GeneralBusinessInformation> findByAllByLoanId(@PathVariable("loanId") String id){
        return generalBusinessInformationService.findAllByLoanId(id);
    }

}
