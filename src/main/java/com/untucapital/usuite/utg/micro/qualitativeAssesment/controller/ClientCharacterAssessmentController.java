package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.ClientCharacterAssessmentService;
import com.untucapital.usuite.utg.model.ClientCharacterAssessment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "clientCharacterAssessment")
public class ClientCharacterAssessmentController {
    @Autowired
    private final ClientCharacterAssessmentService clientCharacterAssessmentService;

    public ClientCharacterAssessmentController(ClientCharacterAssessmentService clientCharacterAssessmentService) {
        this.clientCharacterAssessmentService = clientCharacterAssessmentService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody ClientCharacterAssessment clientCharacterAssessment){
        clientCharacterAssessmentService.save(clientCharacterAssessment);
    }
    //Delete by id
    @DeleteMapping("/delete/{clientCharacterAssessmentId}")
    public void deleteById(@PathVariable("clientCharacterAssessmentId") String id){
        clientCharacterAssessmentService.deleteById(id);
    }
    //Find All By Id
    @GetMapping("/get/{loanId}")
    public List<ClientCharacterAssessment> findAllByLoanId(@PathVariable("loanId") String id){
        return clientCharacterAssessmentService.findAllByLoanId(id);
    }
}
