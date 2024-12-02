package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.DisbursementTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementTicketRepository extends JpaRepository<DisbursementTicket, String> {
    void deleteById(String id);
}
