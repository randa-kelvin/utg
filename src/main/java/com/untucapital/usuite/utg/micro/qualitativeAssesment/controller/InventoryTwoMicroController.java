package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.InventoryTwoMicroService;
import com.untucapital.usuite.utg.model.InventoryTwoMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventoryTwo")
public class InventoryTwoMicroController {
    @Autowired
    private final InventoryTwoMicroService inventoryTwoMicroService;

    public InventoryTwoMicroController(InventoryTwoMicroService inventoryTwoMicroService) {
        this.inventoryTwoMicroService = inventoryTwoMicroService;
    }
    //add
    @PostMapping("/save")
    public void save(@RequestBody InventoryTwoMicro inventoryTwoMicro){
        inventoryTwoMicroService.save(inventoryTwoMicro);
    }
    //delete by id
    @DeleteMapping("/delete/{inventoryTwoId}")
    public void deleteById(@PathVariable("inventoryTwoId") String id){
        inventoryTwoMicroService.deleteById(id);
    }
    //get all by loan id
    @GetMapping("/get/{loanId}")
    public List<InventoryTwoMicro> findAllByLoadId(@PathVariable("loanId") String id){
        return inventoryTwoMicroService.findAllByLoanId(id);
    }
}
