package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.PastPurchasesMicroRepository;
import com.untucapital.usuite.utg.model.PastPurchasesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PastPurchasesMicroService {
    @Autowired
    private final PastPurchasesMicroRepository pastPurchasesMicroRepository;

    public PastPurchasesMicroService(PastPurchasesMicroRepository pastPurchasesMicroRepository) {
        this.pastPurchasesMicroRepository = pastPurchasesMicroRepository;
    }
    //Add
    public void save(PastPurchasesMicro pastPurchasesMicro){
        pastPurchasesMicroRepository.save(pastPurchasesMicro);
    }
    //Delete by loan id
    public void deleteById(String id){
        pastPurchasesMicroRepository.deleteById(id);
    }
    //Find all loan id
    public List<PastPurchasesMicro> findAllByLoanId(String id){
        return pastPurchasesMicroRepository.findAllByLoanId(id);
    }
}
