package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, String> {
    List<Sales> findSalestByLoanIdAndBusinessUnitOrderByMonthAsc(String id, String businessUnit);


}
