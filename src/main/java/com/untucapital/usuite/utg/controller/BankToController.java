package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.BankToRequestDTO;
import com.untucapital.usuite.utg.dto.response.BankToResponseDTO;
import com.untucapital.usuite.utg.service.BankToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankTo")
public class BankToController {

    @Autowired
    private final BankToService bankToService;

    public BankToController(BankToService bankToService) {
        this.bankToService = bankToService;
    }

    @PostMapping("/addBankTo")
    public void add(@RequestBody BankToRequestDTO requestDTO) {
        bankToService.saveBankTo(requestDTO);
    }

    @GetMapping("getBankTo/{loanId}/{bank}")
    public List<BankToResponseDTO> getBankTo(@PathVariable("loanId") String id, @PathVariable("bank") String bank) {
        return bankToService.listBankToByLoanId(id, bank);
    }

    @DeleteMapping("/deleteBankTo/{id}")
    public void deleteById(@PathVariable String id) {
        bankToService.deleteById(id);
    }

}
