package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Integer> {

}
