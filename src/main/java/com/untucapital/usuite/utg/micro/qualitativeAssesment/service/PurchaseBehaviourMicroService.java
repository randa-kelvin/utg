package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.PurchaseBehaviourMicroRepository;
import com.untucapital.usuite.utg.model.PurchaseBehaviourMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseBehaviourMicroService {
    @Autowired
    private final PurchaseBehaviourMicroRepository purchaseBehaviourMicroRepository;

    public PurchaseBehaviourMicroService(PurchaseBehaviourMicroRepository purchaseBehaviourMicroRepository) {
        this.purchaseBehaviourMicroRepository = purchaseBehaviourMicroRepository;
    }
    //Add
    public void save(PurchaseBehaviourMicro purchaseBehaviourMicro){
        purchaseBehaviourMicroRepository.save(purchaseBehaviourMicro);
    }
    //Delete by id
    public void deleteById(String id){
        purchaseBehaviourMicroRepository.deleteById(id);
    }
    //Find all by loan id
    public List<PurchaseBehaviourMicro> findAllByLoanId(String id){
        return purchaseBehaviourMicroRepository.findAllByLoanId(id);
    }
}
