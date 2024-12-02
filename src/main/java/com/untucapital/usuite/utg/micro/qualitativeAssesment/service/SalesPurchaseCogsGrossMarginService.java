package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.SalesPurchaseCogsGrossMarginRepository;
import com.untucapital.usuite.utg.model.SalesPurchaseCogsGrossMargin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesPurchaseCogsGrossMarginService {
    @Autowired
    private final SalesPurchaseCogsGrossMarginRepository salesPurchaseCogsGrossMarginRepository;

    public SalesPurchaseCogsGrossMarginService(SalesPurchaseCogsGrossMarginRepository salesPurchaseCogsGrossMarginRepository) {
        this.salesPurchaseCogsGrossMarginRepository = salesPurchaseCogsGrossMarginRepository;
    }
    //Add
    public void save(SalesPurchaseCogsGrossMargin salesPurchaseCogsGrossMargin){
        salesPurchaseCogsGrossMarginRepository.save(salesPurchaseCogsGrossMargin);
    }
    //Delete by Id
    public void deleteById(String id){
        salesPurchaseCogsGrossMarginRepository.deleteById(id);
    }
    //Find All By Loan  Id
    public List<SalesPurchaseCogsGrossMargin> findAllByLoanId(String id){
        return salesPurchaseCogsGrossMarginRepository.findAlByLoanId(id);
    }
}
