package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.PurchaseBehaviourMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseBehaviourMicroRepository extends JpaRepository<PurchaseBehaviourMicro, String> {
    List<PurchaseBehaviourMicro> findAllByLoanId(String id);
}
