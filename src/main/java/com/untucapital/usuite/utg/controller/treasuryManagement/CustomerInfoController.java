package com.untucapital.usuite.utg.controller.treasuryManagement;

import com.untucapital.usuite.utg.model.treasuryManagement.CustomerInfo;
import com.untucapital.usuite.utg.service.treasuryManagement.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika
 ** @created 2024/01/25
 **************************************
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/treasury_management/customer-info")
public class CustomerInfoController {
    private final CustomerInfoService customerInfoService;

    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerInfo createCustomerInfo(@RequestBody CustomerInfo customerInfo) {
        return customerInfoService.createCustomerInfo(customerInfo);
    }

    //READ
    @GetMapping("/{id}")
    public CustomerInfo getCustomerInfo(@PathVariable Integer id) {
        return customerInfoService.getCustomerInfo(id);
    }

    //LIST
    @GetMapping
    public List<CustomerInfo> listCustomerInfo() {
        return customerInfoService.listCustomerInfo();
    }

    //UPDATE
    @PutMapping
    public CustomerInfo updateCustomerInfo(@RequestBody CustomerInfo customerInfo) {
        return customerInfoService.updateCustomerInfo(customerInfo);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomerInfo(@PathVariable("id") Integer id) {
        return customerInfoService.deleteCustomerInfo(id);
    }
}
