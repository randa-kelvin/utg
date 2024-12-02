package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ProposedCollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposedCollateralSecurityRepository extends JpaRepository<ProposedCollateralSecurity, String> {
    List<ProposedCollateralSecurity> findByLoanId(@Param("loanId") Long loanId);
}
