package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.BusinessRequestDTO;
import com.untucapital.usuite.utg.dto.response.BusinessResponseDTO;
import com.untucapital.usuite.utg.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @GetMapping("/getBusiness")
    public List<BusinessResponseDTO> list() {
        return businessService.listAllBusiness();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> get(@PathVariable Integer id) {
        try {
            BusinessResponseDTO business = businessService.getBusiness(id);
            return new ResponseEntity<BusinessResponseDTO>(business, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<BusinessResponseDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addBusiness")
    public void add(@RequestBody BusinessRequestDTO business) {
        businessService.saveBusiness(business);
    }


    @DeleteMapping("/deleteBusiness/{id}")
    public void delete(@PathVariable Integer id) {

        businessService.deleteBusiness(id);
    }
}
