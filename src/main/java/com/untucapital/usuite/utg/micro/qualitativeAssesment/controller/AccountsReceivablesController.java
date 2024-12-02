package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.AccountsReceivablesService;
import com.untucapital.usuite.utg.model.AccountsReceivable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "accountsReceivables")
public class AccountsReceivablesController {
    @Autowired
    private final AccountsReceivablesService accountsReceivablesService;

    public AccountsReceivablesController(AccountsReceivablesService accountsReceivablesService) {
        this.accountsReceivablesService = accountsReceivablesService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody AccountsReceivable accountsReceivable){
        accountsReceivablesService.save(accountsReceivable);
    }
    //Delete By id
    @DeleteMapping("/delete/{accountsReceivablesId}")
    public void deleteById(@PathVariable("accountsReceivablesId") String id){
        accountsReceivablesService.deleteById(id);
    }
    //Find All By PageItem id
    @GetMapping("/get/{loanId}")
    public List<AccountsReceivable> findAllByLoanId(@PathVariable("loanId") String id){
        return accountsReceivablesService.findAllByLoanId(id);
    }
}
