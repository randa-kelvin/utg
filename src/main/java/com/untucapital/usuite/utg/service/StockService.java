package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.StockRequestDTO;
import com.untucapital.usuite.utg.dto.response.StockResponseDTO;
import com.untucapital.usuite.utg.model.Stock;
import com.untucapital.usuite.utg.repository.StockRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Transactional(value = "transactionManager")
    public void  saveStock(StockRequestDTO request) {

        Stock stock = new Stock();
        BeanUtils.copyProperties(request,stock);
        stockRepository.save(stock);
    }

    @Transactional(value = "transactionManager")
    public List<StockResponseDTO> getStockByLoanId(String loanId) {

        List<StockResponseDTO> response = new ArrayList<>();
        List<Stock> stockList = stockRepository.findbyLoanId(loanId);

        for (Stock stock : stockList) {
            StockResponseDTO responseDTO = new StockResponseDTO();
            BeanUtils.copyProperties(stock, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteStockById(String id) {
        stockRepository.deleteById(id);
    }
}
