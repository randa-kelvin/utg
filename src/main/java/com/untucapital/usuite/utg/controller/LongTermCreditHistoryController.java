package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.LongTermCreditHistoryRequestDTO;
import com.untucapital.usuite.utg.dto.response.LongTermCreditHistoryResponseDTO;
import com.untucapital.usuite.utg.service.LongTermCreditHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("long_term_credit_history")
public class LongTermCreditHistoryController {

    @Autowired
    LongTermCreditHistoryService creditHistoryService;

    private static final Logger log = LoggerFactory.getLogger(LongTermCreditHistoryController.class);

    @GetMapping("/get/{loanId}")
    public List<LongTermCreditHistoryResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return creditHistoryService.getCreditHistoryByLoanId(loanId);
    }

    @PostMapping("/save")
    public void add(@RequestBody LongTermCreditHistoryRequestDTO creditHistory) {
        creditHistoryService.saveCreditHistory(creditHistory);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        creditHistoryService.deleteCreditHistory(id);
    }
}
