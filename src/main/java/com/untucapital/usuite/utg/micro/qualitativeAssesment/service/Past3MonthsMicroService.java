package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.Past3MonthsMicroRepository;
import com.untucapital.usuite.utg.model.Past3MonthsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Past3MonthsMicroService {
    @Autowired
    private final Past3MonthsMicroRepository past3MonthsMicroRepository;

    public Past3MonthsMicroService(Past3MonthsMicroRepository past3MonthsMicroRepository) {
        this.past3MonthsMicroRepository = past3MonthsMicroRepository;
    }
    //Add
    public void save(Past3MonthsMicro past3MonthsMicro){
        past3MonthsMicroRepository.save(past3MonthsMicro);
    }
    //Delete by id
    public void deleteById(String id){
        past3MonthsMicroRepository.deleteById(id);
    }
    //Find all by loan id
    public List<Past3MonthsMicro> findAllByLoanId(String id){
        return past3MonthsMicroRepository.findAllByLoanId(id);
    }
}
