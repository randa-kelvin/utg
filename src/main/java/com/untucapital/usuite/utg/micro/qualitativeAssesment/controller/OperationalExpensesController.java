package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.OperationalExpensesService;
import com.untucapital.usuite.utg.model.OperationalExpenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "operationalExpenses")
public class OperationalExpensesController {
    @Autowired
    private final OperationalExpensesService operationalExpensesService;

    public OperationalExpensesController(OperationalExpensesService operationalExpensesService) {
        this.operationalExpensesService = operationalExpensesService;
    }

    //Add
    @PostMapping("/save")
    public void save(@RequestBody OperationalExpenses operationalExpenses){
        operationalExpensesService.save(operationalExpenses);
    }

    //Delete by id
    @DeleteMapping("/delete/{operationalExpenses}")
    public void deleteById(@PathVariable("operationalExpenses") String id){
        operationalExpensesService.deleteById(id);
    }

    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<OperationalExpenses> findAllByLoanId(@PathVariable("loanId") String id){
        return operationalExpensesService.findAllByLoanId(id);
    }
}
