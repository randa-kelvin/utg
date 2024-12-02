package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.RealEstateMicroRepository;
import com.untucapital.usuite.utg.model.RealEstateMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstateMicroService {
    @Autowired
    private final RealEstateMicroRepository realEstateMicroRepository;

    public RealEstateMicroService(RealEstateMicroRepository realEstateMicroRepository) {
        this.realEstateMicroRepository = realEstateMicroRepository;
    }

    public void save(RealEstateMicro realEstateMicro) {
        realEstateMicroRepository.save(realEstateMicro);
    }

    public void deleteById(String id) {
        realEstateMicroRepository.deleteById(id);
    }

    public List<RealEstateMicro> findAllByLoanId(String id) {
        return  realEstateMicroRepository.findAllByLoanId(id);
    }
}
