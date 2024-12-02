package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.BusinessUnitRequestDTO;
import com.untucapital.usuite.utg.dto.response.BusinessUnitResponseDTO;
import com.untucapital.usuite.utg.service.BusinessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("businessUnit")
public class BusinessUnitController {

    @Autowired
    private final BusinessUnitService businessUnitService;

    public BusinessUnitController(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    //Save new Business Unit
    @PostMapping("/saveBusinessUnit")
    public void saveBusinessUnit(@RequestBody BusinessUnitRequestDTO requestDTO){
        businessUnitService.saveBusinessUnit(requestDTO);
    }

    //Get All Business Units
    @GetMapping("/listBusinessUnits")
    public List<BusinessUnitResponseDTO> listBusinessUnits(){
        return businessUnitService.listBusinessUnits();
    }

    //Get Business Unit By Id
    @GetMapping("/getBusinessUnit/{businessUnitId}")
    public  List<BusinessUnitResponseDTO> getBusinessUnit(@PathVariable("businessUnitId") String id){
        return businessUnitService.getBusinessUnitById(id);
    }
    //Get list Business Units by Loan Id
    @GetMapping("getBusinessUnitsByLoanId/{loanId}")
    public List<BusinessUnitResponseDTO> getBusinessUnitsByLoanId(@PathVariable("loanId") String id){
        return businessUnitService.lisBusinessUnitByLoanId(id);
    }

    //Delete Business Unit by Id
    @DeleteMapping("deleteBusinessUnit/{businessUnitId}")
    public void deleteBusinessUnit(@PathVariable("businessUnitId") String id){
        businessUnitService.deleteBusinessUnit(id);
    }
}

