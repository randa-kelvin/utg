package com.untucapital.usuite.utg.micro.qualitativeAssesment.repo;

import com.untucapital.usuite.utg.model.CommentsOnFamilySituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsOnFamilySituationRepository extends JpaRepository<CommentsOnFamilySituation, String> {
    List<CommentsOnFamilySituation> findAllByLoanId(String id);
}

