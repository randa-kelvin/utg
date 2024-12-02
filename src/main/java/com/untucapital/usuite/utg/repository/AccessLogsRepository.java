package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.AccessLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogsRepository extends JpaRepository<AccessLogs, String> {

    @Override
    List<AccessLogs> findAll();
}
