package com.untucapital.usuite.utg.micro.qualitativeAssesment.controller;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.service.MotorVehicleMicroService;
import com.untucapital.usuite.utg.model.MotorVehicleMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("motorVehicle")
public class MotorVehicleMicroController {
    @Autowired
    private final MotorVehicleMicroService motorVehicleMicroService;

    public MotorVehicleMicroController(MotorVehicleMicroService motorVehicleMicroService) {
        this.motorVehicleMicroService = motorVehicleMicroService;
    }
    //Add
    @PostMapping("/save")
    private void save(@RequestBody MotorVehicleMicro motorVehicleMicro){
        motorVehicleMicroService.save(motorVehicleMicro);
    }
    //Delete by id
    @DeleteMapping("/delete/{motorVehicleId}")
    private void deleteByLoanId(@PathVariable("motorVehicleId") String id){
        motorVehicleMicroService.deleteById(id);
    }
    //Find all by loan id
    @GetMapping("/get/{loanId}")
    private List<MotorVehicleMicro> findAllByLoanId(@PathVariable("loanId") String id){
        return  motorVehicleMicroService.findAllByLoanId(id);
    }
}
