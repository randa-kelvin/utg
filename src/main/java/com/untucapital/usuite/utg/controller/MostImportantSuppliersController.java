package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.MostImportantSuppliersRequestDTO;
import com.untucapital.usuite.utg.dto.response.MostImportantSuppliersResponseDTO;
import com.untucapital.usuite.utg.service.MostImportantSuppliersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("most_important_suppliers")
public class MostImportantSuppliersController {
    @Autowired
    MostImportantSuppliersService mostImportantSuppliersService;

    private static final Logger log = LoggerFactory.getLogger(MostImportantSuppliersController.class);

    @GetMapping("/get/{loanId}")
    public List<MostImportantSuppliersResponseDTO> getByLoanId(@PathVariable("loanId") String loanId) {
        return mostImportantSuppliersService.getMostImportantSuppliersByLoanId(loanId);
    }

    @PostMapping("/most_important_suppliers")
    public void add(@RequestBody MostImportantSuppliersRequestDTO mostImportantSuppliers) {
        mostImportantSuppliersService.saveMostImportantSuppliers(mostImportantSuppliers);
    }

    @DeleteMapping("/most_important_suppliers/{id}")
    public void delete(@PathVariable String id) {
        mostImportantSuppliersService.deleteMostImportantSuppliers(id);
    }

}
