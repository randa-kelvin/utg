package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.SourceOfFundsRequestDTO;
import com.untucapital.usuite.utg.dto.response.SourceOfFundsResponseDTO;
import com.untucapital.usuite.utg.service.SourceOfFundsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("source_of_funds")
public class SourceOfFundsController {

    @Autowired
    SourceOfFundsService sourceOfFundsService;

    private static final Logger log = LoggerFactory.getLogger(SourceOfFundsController.class);

    @GetMapping("/get/{loanId}")
    public List<SourceOfFundsResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return sourceOfFundsService.getSourceOfFundsByLoanId(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody SourceOfFundsRequestDTO sourceOfFunds) {
        sourceOfFundsService.saveSourceOfFunds(sourceOfFunds);
    }

    @DeleteMapping("/delete/{Id}")
    public void delete(@PathVariable("Id") String id){
        sourceOfFundsService.deleteSourceOfFunds(id);
    }

}
