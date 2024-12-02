package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.CapitalisationOfProfitRequestDTO;
import com.untucapital.usuite.utg.dto.response.CapitalisationOfProfitResponseDTO;
import com.untucapital.usuite.utg.service.CapitalisationOfProfitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("capitalisationOfProfit")
public class CapitalisationOfProfitController {
    @Autowired
    CapitalisationOfProfitService capitalisationOfProfitService;

    private static final Logger log = LoggerFactory.getLogger(CapitalisationOfProfitController.class);

    @PostMapping("/add")
    public void add(@RequestBody CapitalisationOfProfitRequestDTO capitalisationOfProfit) {
        capitalisationOfProfitService.add(capitalisationOfProfit);
    }

    @GetMapping("/get/{loanId}")
    public List<CapitalisationOfProfitResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return capitalisationOfProfitService.get(loanId);
    }

    @DeleteMapping("/delete/{id}")
    public void add(@PathVariable String id) {
        capitalisationOfProfitService.delete(id);
    }
}
