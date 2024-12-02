package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.PastPurchasesMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PastPurchasesMicroRepository extends JpaRepository<PastPurchasesMicro, String> {
    List<PastPurchasesMicro> findAllByLoanId(String id);
}
