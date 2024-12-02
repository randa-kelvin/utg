package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.MotorVehicleMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorVehicleMicroRepository extends JpaRepository<MotorVehicleMicro, String> {
    List<MotorVehicleMicro> findAllByLoanId(String id);
}
