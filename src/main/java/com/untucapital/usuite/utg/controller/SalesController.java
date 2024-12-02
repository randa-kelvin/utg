package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.SalesRequestDTO;
import com.untucapital.usuite.utg.dto.response.SalesResponseDTO;
import com.untucapital.usuite.utg.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("sales")
public class SalesController {
    @Autowired
    SalesService salesService;

    @GetMapping("getSales")
    public List<SalesResponseDTO> list() {
        return salesService.listAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesResponseDTO> get(@PathVariable String id) {
        try {
            SalesResponseDTO sales = salesService.getSales(id);
            return new ResponseEntity<SalesResponseDTO>(sales, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<SalesResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addSales")
    public void add(@RequestBody SalesRequestDTO sales) {
        salesService.saveSales(sales);
    }


    @DeleteMapping("/deleteSales/{Id}")
    public void delete(@PathVariable("Id") String id) {

        salesService.deleteSales(id);
    }
    @DeleteMapping("/delete/{Id}")
    public void deleteById(@PathVariable("Id") String id){
        salesService.deleteById(id);
    }

    //Get list Business Units by Loan Id and groupBy Bsn unit
    @GetMapping("getSalesByLoanIdAndBusinessUnits/{loanId}/{businessUnit}")
    public List<SalesResponseDTO> getSalesByLoanIdAndBusinessUnits(@PathVariable("loanId") String id, @PathVariable("businessUnit") String businessUnit){
        return salesService.lisSalesByLoanId(id, businessUnit);
    }

    }

