package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.PrivateExpensesService;
import com.untucapital.usuite.utg.model.PrivateExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "privateExpenses")
public class PrivateExpensesController {
    @Autowired
    private final PrivateExpensesService privateExpensesService;

    public PrivateExpensesController(PrivateExpensesService privateExpensesService) {
        this.privateExpensesService = privateExpensesService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody PrivateExpenses privateExpenses){
        privateExpensesService.save(privateExpenses);
    }
    //Delete By Id
    @DeleteMapping("/delete/{privateExpensesId}")
    public void deleteById(@PathVariable("privateExpensesId") String id){
        privateExpensesService.deleteById(id);
    }
    //Find All Loan Id
    @GetMapping("/get/{loanId}")
    public List<PrivateExpenses> findAllByLoanId(@PathVariable("loanId") String id){
        return  privateExpensesService.findAllByLoanId(id);
    }
}
