package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.StockRequestDTO;
import com.untucapital.usuite.utg.dto.response.StockResponseDTO;
import com.untucapital.usuite.utg.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

    @PostMapping("/addstock")
    public void add(@RequestBody StockRequestDTO stock) {
        stockService.saveStock(stock);
    }

    @GetMapping("/get_stock/{loanId}")
    public List<StockResponseDTO> getStockByLoanId(@PathVariable("loanId") String loanId) {
        return stockService.getStockByLoanId(loanId);
    }
    @DeleteMapping("/deleteStock/{id}")
    public void deleteStockById(@PathVariable String id){
        stockService.deleteStockById(id);
    }
}
