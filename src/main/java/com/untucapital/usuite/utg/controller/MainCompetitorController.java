package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.MainCompetitorRequestDTO;
import com.untucapital.usuite.utg.dto.response.MainCompetitorResponseDTO;
import com.untucapital.usuite.utg.service.MainCompetitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "main_competitor")
public class MainCompetitorController {
    @Autowired
    MainCompetitorService mainCompetitorService;

    private static final Logger log = LoggerFactory.getLogger(MainCompetitorController.class);

    @GetMapping("/get/{loanId}")
    public List<MainCompetitorResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return mainCompetitorService.getMainCompetitorByLoanId(loanId);
    }

    @PostMapping("/save")
    public void add(@RequestBody MainCompetitorRequestDTO mainCompetitor) {
        mainCompetitorService.saveMainCompetitor(mainCompetitor);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        mainCompetitorService.deleteMainCompetitor(id);
    }

}
