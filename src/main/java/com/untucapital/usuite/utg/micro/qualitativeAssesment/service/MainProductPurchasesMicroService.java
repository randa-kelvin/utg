package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.MainProductPurchasesMicroRepository;
import com.untucapital.usuite.utg.model.MainProductPurchasesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainProductPurchasesMicroService {
    @Autowired
    private final MainProductPurchasesMicroRepository mainProductPurchasesMicroRepository;

    public MainProductPurchasesMicroService(MainProductPurchasesMicroRepository mainProductPurchasesMicroRepository) {
        this.mainProductPurchasesMicroRepository = mainProductPurchasesMicroRepository;
    }
    //Add
    public void save(MainProductPurchasesMicro mainProductPurchasesMicro){
        mainProductPurchasesMicroRepository.save(mainProductPurchasesMicro);
    }
    //Delete by id
    public  void deleteById(String id){
        mainProductPurchasesMicroRepository.deleteById(id);
    }
    //Find all by loan id
    public List<MainProductPurchasesMicro> findAllLoanId(String id){
        return mainProductPurchasesMicroRepository.findAllByLoanId(id);
    }
}
