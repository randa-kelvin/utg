package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.GuarantorRepository;
import com.untucapital.usuite.utg.model.Guarantor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuarantorService {
    @Autowired
    private final GuarantorRepository guarantorRepository;

    public GuarantorService(GuarantorRepository guarantorRepository) {
        this.guarantorRepository = guarantorRepository;
    }
    //Add
    public void save(Guarantor guarantor){
        guarantorRepository.save(guarantor);
    }
    //Delete by id
    public void deleteById(String id){
        guarantorRepository.deleteById(id);
    }
    //Find All By id
    public List<Guarantor> findAllByLoanId(String id){
        return guarantorRepository.findAllByLoanId(id);
    }
}
