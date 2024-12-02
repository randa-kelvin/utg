package com.untucapital.usuite.utg.pos.repository;

import com.untucapital.usuite.utg.pos.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
