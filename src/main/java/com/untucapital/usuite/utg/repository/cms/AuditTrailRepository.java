package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Integer>{
}
