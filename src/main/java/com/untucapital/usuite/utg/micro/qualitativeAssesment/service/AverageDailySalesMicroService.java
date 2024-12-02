package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.AverageDailySalesMicroRepository;
import com.untucapital.usuite.utg.model.AverageDailySalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AverageDailySalesMicroService {
    @Autowired
    private final AverageDailySalesMicroRepository averageDailySalesMicroRepository;

    public AverageDailySalesMicroService(AverageDailySalesMicroRepository averageDailySalesMicroRepository) {
        this.averageDailySalesMicroRepository = averageDailySalesMicroRepository;
    }

    //Add
    public void save(AverageDailySalesMicro averageDailySalesMicro) {
        averageDailySalesMicroRepository.save(averageDailySalesMicro);
    }

    //Delete by id
    public void deleteById(String id) {
        averageDailySalesMicroRepository.deleteById(id);
    }

    //Find All By loan id
    public List<AverageDailySalesMicro> findAllByLoanId(String id) {
        return averageDailySalesMicroRepository.findAllByLoanId(id);
    }
}
