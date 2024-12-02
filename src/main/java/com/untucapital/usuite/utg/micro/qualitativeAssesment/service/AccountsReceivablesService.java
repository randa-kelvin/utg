package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.AccountsReceivableRepository;
import com.untucapital.usuite.utg.model.AccountsReceivable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsReceivablesService {
    @Autowired
    private final AccountsReceivableRepository accountsReceivableRepository;

    public AccountsReceivablesService(AccountsReceivableRepository accountsReceivableRepository) {
        this.accountsReceivableRepository = accountsReceivableRepository;
    }

    //Add
    public void save(AccountsReceivable accountsReceivable) {
        accountsReceivableRepository.save(accountsReceivable);
    }

    //Delete by id
    public void deleteById(String id) {
        accountsReceivableRepository.deleteById(id);
    }

    //Find All PageItem By id
    public List<AccountsReceivable> findAllByLoanId(String id) {
        return accountsReceivableRepository.findAlByLoanId(id);
    }
}
