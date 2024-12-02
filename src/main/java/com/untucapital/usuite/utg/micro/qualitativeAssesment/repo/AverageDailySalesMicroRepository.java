package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.AverageDailySalesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AverageDailySalesMicroRepository extends JpaRepository<AverageDailySalesMicro, String > {
    List<AverageDailySalesMicro> findAllByLoanId(String id);
}
