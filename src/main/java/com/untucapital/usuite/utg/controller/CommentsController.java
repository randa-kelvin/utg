package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.CommentsRequestDTO;
import com.untucapital.usuite.utg.dto.response.CommentsResponseDTO;
import com.untucapital.usuite.utg.service.CommentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;

    private static final Logger log = LoggerFactory.getLogger(CommentsController.class);

    @PostMapping("/addComments")
    public void add(@RequestBody CommentsRequestDTO comments) {

        commentsService.saveComments(comments);
    }

    @GetMapping("/getComments/{loanId}")
    public List<CommentsResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return commentsService.getByLoanId(loanId);
    }
}

