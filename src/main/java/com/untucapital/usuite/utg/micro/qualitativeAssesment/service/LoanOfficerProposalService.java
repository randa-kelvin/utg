package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;



import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.LoanOfficerProposalRepository;
import com.untucapital.usuite.utg.model.LoanOfficerProposal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanOfficerProposalService {
    @Autowired
    private final LoanOfficerProposalRepository loanOfficerProposalRepository;

    public LoanOfficerProposalService(LoanOfficerProposalRepository loanOfficerProposalRepository) {
        this.loanOfficerProposalRepository = loanOfficerProposalRepository;
    }
    //Add
    public void save(LoanOfficerProposal loanOfficerProposal){
        loanOfficerProposalRepository.save(loanOfficerProposal);
    }
    //Delete by id
    public void deleteById(String id){
        loanOfficerProposalRepository.deleteById(id);
    }
    //Find all by loan id
    public List<LoanOfficerProposal> findAllByLoanId(String id){
        return  loanOfficerProposalRepository.findAllByLoanId(id);
    }
}
