package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, String> {

    Cities findCitiesById(String id);
}
