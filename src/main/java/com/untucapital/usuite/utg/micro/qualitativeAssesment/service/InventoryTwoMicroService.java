package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.InventoryTwoMicroRepository;
import com.untucapital.usuite.utg.model.InventoryTwoMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryTwoMicroService {
    @Autowired
    private final InventoryTwoMicroRepository inventoryTwoMicroRepository;

    public InventoryTwoMicroService(InventoryTwoMicroRepository inventoryTwoMicroRepository) {
        this.inventoryTwoMicroRepository = inventoryTwoMicroRepository;
    }

    public void save(InventoryTwoMicro inventoryTwoMicro) {
        inventoryTwoMicroRepository.save(inventoryTwoMicro);
    }

    public void deleteById(String id) {
        inventoryTwoMicroRepository.deleteById(id);
    }

    public List<InventoryTwoMicro> findAllByLoanId(String id) {
        return inventoryTwoMicroRepository.findAllByLoanId(id);
    }
}
