package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.fcb.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FCBResponseRepository extends JpaRepository<Response, String> {

}
