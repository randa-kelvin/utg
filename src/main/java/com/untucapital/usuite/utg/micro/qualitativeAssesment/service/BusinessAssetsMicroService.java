package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.BusinessAssetsMicroRepository;
import com.untucapital.usuite.utg.model.BusinessAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessAssetsMicroService {
    @Autowired
    private final BusinessAssetsMicroRepository businessAssetsMicroRepository;

    public BusinessAssetsMicroService(BusinessAssetsMicroRepository businessAssetsMicroRepository) {
        this.businessAssetsMicroRepository = businessAssetsMicroRepository;
    }

    public void save(BusinessAssetsMicro businessAssetsMicro) {
        businessAssetsMicroRepository.save(businessAssetsMicro);
    }

    public void deleteById(String id) {
        businessAssetsMicroRepository.deleteById(id);
    }

    public List<BusinessAssetsMicro> findAllByLoanId(String id) {
        return businessAssetsMicroRepository.findAllByLoanId(id);
    }
}
