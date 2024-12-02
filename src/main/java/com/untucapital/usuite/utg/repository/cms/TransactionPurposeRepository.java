package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@Repository
public interface TransactionPurposeRepository extends JpaRepository<TransactionPurpose, Integer> {

    Optional<TransactionPurpose> findByName(String name);
}
