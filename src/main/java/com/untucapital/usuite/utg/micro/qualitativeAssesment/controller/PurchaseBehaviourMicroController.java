package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.PurchaseBehaviourMicroService;
import com.untucapital.usuite.utg.model.PurchaseBehaviourMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchaseBehaviour")
public class PurchaseBehaviourMicroController {
    @Autowired
    private final PurchaseBehaviourMicroService purchaseBehaviourMicroService;

    public PurchaseBehaviourMicroController(PurchaseBehaviourMicroService purchaseBehaviourMicroService) {
        this.purchaseBehaviourMicroService = purchaseBehaviourMicroService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody PurchaseBehaviourMicro purchaseBehaviourMicro){
        purchaseBehaviourMicroService.save(purchaseBehaviourMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{purchaseBehaviourId}")
    public void deleteById(@PathVariable("purchaseBehaviourId") String id){
        purchaseBehaviourMicroService.deleteById(id);
    }
    //Find All by loan ID
    @GetMapping("/get/{loanId}")
    public List<PurchaseBehaviourMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return purchaseBehaviourMicroService.findAllByLoanId(id);
    }

}
