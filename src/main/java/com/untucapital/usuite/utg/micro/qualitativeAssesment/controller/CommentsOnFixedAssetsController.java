package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CommentsOnFixedAssetsService;
import com.untucapital.usuite.utg.model.CommentsOnFixedAssets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "commentsOnFixedAssets")
public class CommentsOnFixedAssetsController {
    @Autowired
    private final CommentsOnFixedAssetsService commentsOnFixedAssetsService;

    public CommentsOnFixedAssetsController(CommentsOnFixedAssetsService commentsOnFixedAssetsService) {
        this.commentsOnFixedAssetsService = commentsOnFixedAssetsService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody CommentsOnFixedAssets commentsOnFixedAssets){
        commentsOnFixedAssetsService.save(commentsOnFixedAssets);
    }
    //Delete by id
    @DeleteMapping("/delete/{commentsOnFixedAssetsId}")
    public void deleteById(@PathVariable("commentsOnFixedAssetsId") String id){
        commentsOnFixedAssetsService.deleteById(id);
    }
    //Find All By loan id
    @GetMapping("/get/{loanId}")
    public List<CommentsOnFixedAssets> findAllById(@PathVariable("loanId") String id){
        return commentsOnFixedAssetsService.findAllByLoanId(id);
    }
}
