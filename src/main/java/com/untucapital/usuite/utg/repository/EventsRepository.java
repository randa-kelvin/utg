package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, String> {

    Events findEventsById(String id);
}
