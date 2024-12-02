package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.DailySalesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailySalesMicroRepository extends JpaRepository<DailySalesMicro, String > {
    List<DailySalesMicro> findAllByLoanId(String id);
}
