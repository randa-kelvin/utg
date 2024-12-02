package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.SalesRequestDTO;
import com.untucapital.usuite.utg.dto.response.SalesResponseDTO;
import com.untucapital.usuite.utg.model.Sales;
import com.untucapital.usuite.utg.repository.SalesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    @Transactional(value = "transactionManager")
    public List<SalesResponseDTO> listAllSales() {

        List<SalesResponseDTO> result = new ArrayList<SalesResponseDTO>();
        List<Sales> salesList = salesRepository.findAll();

        for (Sales sales : salesList) {
            SalesResponseDTO responseDTO = new SalesResponseDTO();
            BeanUtils.copyProperties(sales, responseDTO);
            result.add(responseDTO);
        }

        return result;
    }

    @Transactional(value = "transactionManager")
    public void saveSales(SalesRequestDTO request) {

        Sales sales = new Sales();
        BeanUtils.copyProperties(request, sales);
        salesRepository.save(sales);
    }

    @Transactional(value = "transactionManager")
    public SalesResponseDTO getSales(String id) {

        SalesResponseDTO sales = new SalesResponseDTO();
        Sales sales1 = salesRepository.findById(id).get();
        BeanUtils.copyProperties(sales1, sales);

        return sales;
    }

    @Transactional(value = "transactionManager")
    public void deleteSales(String id) {
        salesRepository.deleteById(id);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<SalesResponseDTO> lisSalesByLoanId(String id, String businessUnit){

        List<SalesResponseDTO> response = new ArrayList<>();
        List<Sales>salesList = salesRepository.findSalestByLoanIdAndBusinessUnitOrderByMonthAsc(id, businessUnit);

        for (Sales sales : salesList) {
            SalesResponseDTO responseDTO = new SalesResponseDTO();
            BeanUtils.copyProperties(sales, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteById(String id) {
        salesRepository.deleteById(id);
    }
}