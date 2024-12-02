package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadStatusRepository extends JpaRepository<LeadStatus, String> {

    LeadStatus findLeadStatusById(String leadId);
}
