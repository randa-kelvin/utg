package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.CommentsOnAccountReceivablesService;
import com.untucapital.usuite.utg.model.CommentsOnAccountReceivables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "commentsOnAccountReceivables")
public class CommentsOnAccountReceivablesController {
    @Autowired
    private final CommentsOnAccountReceivablesService commentsOnAccountReceivablesService;

    public CommentsOnAccountReceivablesController(CommentsOnAccountReceivablesService commentsOnAccountReceivablesService) {
        this.commentsOnAccountReceivablesService = commentsOnAccountReceivablesService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody CommentsOnAccountReceivables commentsOnAccountReceivables){
        commentsOnAccountReceivablesService.save(commentsOnAccountReceivables);
    }
    //Delete by id
    @DeleteMapping("/delete/{CommentsOnAccountReceivablesId}")
    public void deleteById(@PathVariable("CommentsOnAccountReceivablesId") String id){
        commentsOnAccountReceivablesService.deleteByIid(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    public List<CommentsOnAccountReceivables> findAllById(@PathVariable("loanId") String id){
        return commentsOnAccountReceivablesService.findAllByLoanId(id);
    }
}
