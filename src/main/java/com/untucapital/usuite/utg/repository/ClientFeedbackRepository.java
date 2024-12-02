package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientFeedbackRepository extends JpaRepository<ClientFeedback, String> {


//    ClientLoanEnquiry findClientFeedbackById (String id);
//
////    List<ClientLoanEnquiry> findClientLoansByBranchName(String branchName);
//    List<ClientLoanEnquiry> findClientRepositoryByUserId(String userId);

    ClientFeedback findClientFeedbackById (String id);

    //    List<ClientLoanEnquiry> findClientLoansByBranchName(String branchName);
    List<ClientFeedback> findClientRepositoryByUserId(String userId);


    List<ClientFeedback> findClientFeedbackByUserId(String userId);
}
