package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Zones;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ZonesRepository extends JpaRepository<Zones, String> {

    Zones findZonesById(String id);
}
