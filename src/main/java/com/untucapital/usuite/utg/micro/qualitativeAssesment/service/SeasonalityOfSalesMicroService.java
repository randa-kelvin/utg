package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.SeasonalityOfSalesMicroRepository;
import com.untucapital.usuite.utg.model.SeasonalityOfSalesMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonalityOfSalesMicroService {
    @Autowired
    private final SeasonalityOfSalesMicroRepository seasonalityOfSalesMicroRepository;

    public SeasonalityOfSalesMicroService(SeasonalityOfSalesMicroRepository seasonalityOfSalesMicroRepository) {
        this.seasonalityOfSalesMicroRepository = seasonalityOfSalesMicroRepository;
    }
    //Add
    public void save(SeasonalityOfSalesMicro seasonalityOfSalesMicro){
        seasonalityOfSalesMicroRepository.save(seasonalityOfSalesMicro);
    }
    //delete by loan id
    public void deleteById(String id){
        seasonalityOfSalesMicroRepository.deleteById(id);
    }
    //find all by loan id
    public List<SeasonalityOfSalesMicro> findAllByLoanId(String id){
        return seasonalityOfSalesMicroRepository.findAllByLoanId(id);
    }
}
