package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.LoanRequestMicroRepository;
import com.untucapital.usuite.utg.model.LoanRequestMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRequestMicroService {
    @Autowired
    private final LoanRequestMicroRepository loanRequestMicroRepository;

    public LoanRequestMicroService(LoanRequestMicroRepository loanRequestMicroRepository) {
        this.loanRequestMicroRepository = loanRequestMicroRepository;
    }


    public void save(LoanRequestMicro loanRequestMicro) {
        loanRequestMicroRepository.save(loanRequestMicro);
    }

    public void deleteById(String id) {
        loanRequestMicroRepository.deleteById(id);
    }

    public List<LoanRequestMicro> findAllByLoanId(String id) {
        return loanRequestMicroRepository.findAllByLoanId(id);
    }
}
