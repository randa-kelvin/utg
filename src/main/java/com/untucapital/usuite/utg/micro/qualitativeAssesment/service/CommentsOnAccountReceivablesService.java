package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CommentsOnAccountReceivablesRepository;
import com.untucapital.usuite.utg.model.CommentsOnAccountReceivables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsOnAccountReceivablesService {

    @Autowired
    private final CommentsOnAccountReceivablesRepository commentsOnAccountReceivablesRepository;

    public CommentsOnAccountReceivablesService(CommentsOnAccountReceivablesRepository commentsOnAccountReceivablesRepository) {
        this.commentsOnAccountReceivablesRepository = commentsOnAccountReceivablesRepository;
    }
    //Add
    public void save(CommentsOnAccountReceivables commentsOnAccountReceivables){
        commentsOnAccountReceivablesRepository.save(commentsOnAccountReceivables);
    }
    //Delete by id
    public void deleteByIid(String id){
        commentsOnAccountReceivablesRepository.deleteById(id);
    }
    //Find all by loan id
    public List<CommentsOnAccountReceivables> findAllByLoanId(String id){
        return commentsOnAccountReceivablesRepository.findAllByLoanId(id);
    }
}
