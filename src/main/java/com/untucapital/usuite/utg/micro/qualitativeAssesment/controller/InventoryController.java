package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.InventoryService;
import com.untucapital.usuite.utg.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    @Autowired
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    //Add
    @PostMapping("/save")
    public void save(@RequestBody Inventory inventory){
        inventoryService.save(inventory);
    }
    //Delete by id
    @DeleteMapping("/delete/{inventoryId}")
    public void deleteById(@PathVariable("inventoryId") String id){
        inventoryService.deleteById(id);
    }
    //Find all by id
    @GetMapping("/get/{loanId}")
    public List<Inventory> findAllByLoanId(@PathVariable("loanId") String id){
        return inventoryService.findAllByLoanId(id);
    }
}
