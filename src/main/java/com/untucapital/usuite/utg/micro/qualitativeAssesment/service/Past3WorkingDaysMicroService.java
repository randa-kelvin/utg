package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.Past3WorkingDaysMicroRepository;
import com.untucapital.usuite.utg.model.Past3WorkingDaysMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Past3WorkingDaysMicroService {
    @Autowired
    private final Past3WorkingDaysMicroRepository past3WorkingDaysMicroRepository;

    public Past3WorkingDaysMicroService(Past3WorkingDaysMicroRepository past3WorkingDaysMicroRepository) {
        this.past3WorkingDaysMicroRepository = past3WorkingDaysMicroRepository;
    }
    //Add
    public void save(Past3WorkingDaysMicro past3WorkingDaysMicro){
        past3WorkingDaysMicroRepository.save(past3WorkingDaysMicro);
    }
    //Delete by id
    public void deleteById(String id){
        past3WorkingDaysMicroRepository.deleteById(id);
    }
    //Find all by loan id
    public List<Past3WorkingDaysMicro> findAllByLoanId(String id){
        return past3WorkingDaysMicroRepository.findAllByLoanId(id);
    }
}
