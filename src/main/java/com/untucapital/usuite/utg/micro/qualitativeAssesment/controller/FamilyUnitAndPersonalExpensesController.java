package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.FamilyUnitAndPersonalExpensesService;
import com.untucapital.usuite.utg.model.FamilyUnitAndPersonalExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "familyUnitAndPersonalExpenses")
public class FamilyUnitAndPersonalExpensesController {
    @Autowired
    private final FamilyUnitAndPersonalExpensesService familyUnitAndPersonalExpensesService;


    public FamilyUnitAndPersonalExpensesController(FamilyUnitAndPersonalExpensesService familyUnitAndPersonalExpensesService) {
        this.familyUnitAndPersonalExpensesService = familyUnitAndPersonalExpensesService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody FamilyUnitAndPersonalExpenses familyUnitAndPersonalExpenses){
        familyUnitAndPersonalExpensesService.save(familyUnitAndPersonalExpenses);
    }
    //Delete By Id
    @DeleteMapping("/delete/{familyUnitAndPersonalExpensesId}")
    public void deleteById(@PathVariable("familyUnitAndPersonalExpensesId") String id){
        familyUnitAndPersonalExpensesService.deleteById(id);
    }
    //Find All BY Id
    @GetMapping("/get/{loanId}")
    public List<FamilyUnitAndPersonalExpenses> findAllByLoanId(@PathVariable("loanId") String id){
        return familyUnitAndPersonalExpensesService.findAllByLoanId(id);
    }

}
