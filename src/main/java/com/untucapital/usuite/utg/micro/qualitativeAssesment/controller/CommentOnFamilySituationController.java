package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CommentsOnFamilySituationService;
import com.untucapital.usuite.utg.model.CommentsOnFamilySituation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "commentsOnFamilySituation")
public class CommentOnFamilySituationController {
    @Autowired
    private final CommentsOnFamilySituationService commentsOnFamilySituationService;

    public CommentOnFamilySituationController(CommentsOnFamilySituationService commentsOnFamilySituationService) {
        this.commentsOnFamilySituationService = commentsOnFamilySituationService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody CommentsOnFamilySituation commentsOnFamilySituation){
        commentsOnFamilySituationService.save(commentsOnFamilySituation);
    }
    //Delete By Id
    @DeleteMapping("delete/{commentsOnFamilySituationId}")
    public void deleteById(@PathVariable("commentsOnFamilySituationId") String id){
        commentsOnFamilySituationService.deleteByLoanId(id);
    }
    //Find All By Loan Id
    @GetMapping("get/{loanId}")
    public List<CommentsOnFamilySituation> findAllByLoanId(@PathVariable("loanId") String id){
        return commentsOnFamilySituationService.findAllByLoanId(id);
    }
}
