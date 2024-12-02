package com.untucapital.usuite.utg.pos.controller;

import com.untucapital.usuite.utg.pos.dto.POSSupplierDto;
import com.untucapital.usuite.utg.pos.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@RestController
@RequestMapping(value = "pos/supplier")

@Component("posSupplierController")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    //Save Supplier
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public POSSupplierDto saveSupplier(@RequestBody POSSupplierDto posSupplier) {
        return supplierService.saveSupplier(posSupplier);
    }

    //Get Supplier By Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    //Get all Suppliers
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<POSSupplierDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    //Update Supplier
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto updateSupplier(@RequestBody POSSupplierDto posSupplier) {
        System.out.println(posSupplier.toString());
        return supplierService.updateSupplier(posSupplier);
    }

    //Delete Supplier
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public POSSupplierDto deleteSupplier(@PathVariable Integer id) {

        return supplierService.deleteSupplier(id);
    }
}
