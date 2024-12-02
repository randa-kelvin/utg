package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface IndustryRepository extends JpaRepository<Industry, String> {
    Industry findIndustriesById(String id);

    Industry findByName(String name);


}

