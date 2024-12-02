package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.SalesPurchaseCogsGrossMargin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesPurchaseCogsGrossMarginRepository extends JpaRepository<SalesPurchaseCogsGrossMargin, String>{
    List<SalesPurchaseCogsGrossMargin> findAlByLoanId(String id);
}
