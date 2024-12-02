package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.InventoryRepository;
import com.untucapital.usuite.utg.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    //Add
    public void save(Inventory inventory){
        inventoryRepository.save(inventory);
    }
    //Delete by id
    public void deleteById(String id){
        inventoryRepository.deleteById(id);
    }
    //Find All Loan id
    public List<Inventory> findAllByLoanId(String id){
        return inventoryRepository.findAllByLoanId(id);
    }
}
