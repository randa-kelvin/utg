package com.untucapital.usuite.utg.pos.repository;

import com.untucapital.usuite.utg.pos.model.POSParameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface POSParameterRepository extends JpaRepository<POSParameter, Integer> {


}
