package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.MotorVehicleMicroRepository;
import com.untucapital.usuite.utg.model.MotorVehicleMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorVehicleMicroService {
    @Autowired
    private final MotorVehicleMicroRepository motorVehicleMicroRepository;

    public MotorVehicleMicroService(MotorVehicleMicroRepository motorVehicleMicroRepository) {
        this.motorVehicleMicroRepository = motorVehicleMicroRepository;
    }

    public void save(MotorVehicleMicro motorVehicleMicro) {
        motorVehicleMicroRepository.save(motorVehicleMicro);
    }

    public void deleteById(String id) {
        motorVehicleMicroRepository.deleteById(id);
    }

    public List<MotorVehicleMicro> findAllByLoanId(String id) {
        return motorVehicleMicroRepository.findAllByLoanId(id);
    }
}
