package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CommentsOnFamilySituationRepository;
import com.untucapital.usuite.utg.model.CommentsOnFamilySituation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsOnFamilySituationService {
    @Autowired
    private final CommentsOnFamilySituationRepository commentsOnFamilySituationRepository;

    public CommentsOnFamilySituationService(CommentsOnFamilySituationRepository commentsOnFamilySituationRepository) {
        this.commentsOnFamilySituationRepository = commentsOnFamilySituationRepository;
    }
    //Add
    public void save(CommentsOnFamilySituation commentsOnFamilySituation){
        commentsOnFamilySituationRepository.save(commentsOnFamilySituation);
    }
    //Delete By Id
    public void deleteByLoanId(String id){
        commentsOnFamilySituationRepository.deleteById(id);
    }
    //Find All By Loan Id
    public List<CommentsOnFamilySituation> findAllByLoanId(String id){
        return commentsOnFamilySituationRepository.findAllByLoanId(id);
    }
}
