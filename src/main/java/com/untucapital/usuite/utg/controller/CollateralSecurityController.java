package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.CollateralSecurityRequestDTO;
import com.untucapital.usuite.utg.dto.response.CollateralSecurityResponseDTO;
import com.untucapital.usuite.utg.service.CollateralSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("collateralSecurity")
public class CollateralSecurityController {

    @Autowired
    CollateralSecurityService collateralSecurityService;

    private static final Logger log = LoggerFactory.getLogger(CollateralSecurityController.class);

    @GetMapping("/get/{loanId}")
    public List<CollateralSecurityResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return collateralSecurityService.get(loanId);
    }

    @PostMapping("/add")
    public void add(@RequestBody CollateralSecurityRequestDTO collateralSecurity) {
        collateralSecurityService.add(collateralSecurity);
    }

    @DeleteMapping("/delete/{Id}")
    public void delete(@PathVariable("Id") String id){
        collateralSecurityService.delete(id);
    }


}
