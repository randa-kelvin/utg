package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.CommentsOnAccountReceivables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsOnAccountReceivablesRepository extends JpaRepository<CommentsOnAccountReceivables, String> {
    List<CommentsOnAccountReceivables> findAllByLoanId(String id);
}
