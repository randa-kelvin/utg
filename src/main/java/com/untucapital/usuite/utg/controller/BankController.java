package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.BankRequestDTO;
import com.untucapital.usuite.utg.dto.response.BankResponseDTO;
import com.untucapital.usuite.utg.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankService bankService;

    private static final Logger log = LoggerFactory.getLogger(BankController.class);

    //Adding Bank details
    @PostMapping("/addBank")
    public void add(@RequestBody BankRequestDTO requestDTO) {
        bankService.saveBank(requestDTO);
    }

    //get Bank by loan Id
    @GetMapping("/getByLoanId/{id}")
    public List<BankResponseDTO> get(@PathVariable("id") String loanId) {
        return bankService.getByLoanId(loanId);
    }

}
