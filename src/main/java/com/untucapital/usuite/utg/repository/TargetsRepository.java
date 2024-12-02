package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Targets;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TargetsRepository extends JpaRepository<Targets, String> {

    Targets findTargetsById(String id);


}
