package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.LongTermLiabilityRequestDTO;
import com.untucapital.usuite.utg.dto.response.LongTermLiabilityResponseDTO;
import com.untucapital.usuite.utg.service.LongTermLiabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("longTermLiability")
public class LongTermLiabilityController {
    @Autowired
    LongTermLiabilityService longTermLiabilityService;

    private static final Logger log = LoggerFactory.getLogger(LongTermLiabilityController.class);

    @GetMapping("/get/{loanId}")
    public List<LongTermLiabilityResponseDTO> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return longTermLiabilityService.getLiability(loanId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        longTermLiabilityService.deleteLiability(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody LongTermLiabilityRequestDTO longTermLiability) {
        longTermLiabilityService.saveLiability(longTermLiability);
    }

}
