package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.CommentsOnFixedAssetsRepository;
import com.untucapital.usuite.utg.model.CommentsOnFixedAssets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsOnFixedAssetsService {
    @Autowired
    private final CommentsOnFixedAssetsRepository commentsOnFixedAssetsRepository;

    public CommentsOnFixedAssetsService(CommentsOnFixedAssetsRepository commentsOnFixedAssetsRepository) {
        this.commentsOnFixedAssetsRepository = commentsOnFixedAssetsRepository;
    }
    //Add
    public void save(CommentsOnFixedAssets commentsOnFixedAssets){
        commentsOnFixedAssetsRepository.save(commentsOnFixedAssets);
    }
    //Delete by id
    public void deleteById(String id){
        commentsOnFixedAssetsRepository.deleteById(id);
    }
    //Find All By Loan id
    public List<CommentsOnFixedAssets> findAllByLoanId(String id){
        return commentsOnFixedAssetsRepository.findAllByLoanId(id);
    }
}
