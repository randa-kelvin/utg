package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CashOnHandMicroRepository;
import com.untucapital.usuite.utg.model.CashOnHandMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashOnHandMicroService {
    @Autowired
    private final CashOnHandMicroRepository cashOnHandMicroRepository;

    public CashOnHandMicroService(CashOnHandMicroRepository cashOnHandMicroRepository) {
        this.cashOnHandMicroRepository = cashOnHandMicroRepository;
    }

    //Add
    public void save(CashOnHandMicro cashOnHandMicro){
        cashOnHandMicroRepository.save(cashOnHandMicro);
    }

    //Delete by id
    public  void deleteById(String id){
        cashOnHandMicroRepository.deleteById(id);
    }

    //Find all loan id
    public List<CashOnHandMicro> findAllLoanId(String id){
        return cashOnHandMicroRepository.findAllByLoanId(id);
    }
}
