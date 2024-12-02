package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CostOfSalesMicroRepository;
import com.untucapital.usuite.utg.model.CostOfSalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostOfSalesMicroService {
    @Autowired
    private final CostOfSalesMicroRepository costOfSalesMicroRepository;

    public CostOfSalesMicroService(CostOfSalesMicroRepository costOfSalesMicroRepository) {
        this.costOfSalesMicroRepository = costOfSalesMicroRepository;
    }

    public void save(CostOfSalesMicro costOfSalesMicro) {
        costOfSalesMicroRepository.save(costOfSalesMicro);
    }

    public void deleteById(String id) {
        costOfSalesMicroRepository.deleteById(id);
    }

    public List<CostOfSalesMicro> findAllByLoanId(String id) {
        return costOfSalesMicroRepository.findAllByLoanId(id);
    }
}
