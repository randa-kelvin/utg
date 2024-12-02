package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.RequestCollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestCollateraiSecurityRepository extends JpaRepository<RequestCollateralSecurity, String> {

//    List<RequestCollateraiSecurityRepository> findRequestCollateralSecurityBy(String loanId);
}
