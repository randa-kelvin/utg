package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.DirectCostRequestDTO;
import com.untucapital.usuite.utg.dto.response.DirectCostResponseDTO;
import com.untucapital.usuite.utg.service.DirectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("direct_cost")
public class DirectCostController{

    @Autowired
    private final DirectCostService directCostService;

    public DirectCostController(DirectCostService directCostService) {
        this.directCostService = directCostService;
    }

    //Save Direct Cost
    @PostMapping("/save")
    public void addDirectCost(@RequestBody DirectCostRequestDTO requestDTO){

        directCostService.addDirectCost(requestDTO);
    }

    //Find all Direct Costs and sort by day created
    @GetMapping("/get/{LoanId}")
    public List<DirectCostResponseDTO> getDirectCost(@PathVariable("LoanId") String id){
        return directCostService.allDirectCost(id);
    }

    //Delete Direct Cost by Id
    @DeleteMapping("/delete/{id}")
    public void deleteDirectCost(@PathVariable  String id){
        directCostService.deleteDirectCost(id);
    }


}
