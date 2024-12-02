package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLoanEnquiryRepository extends JpaRepository<ClientLoanEnquiry, String> {


    ClientLoanEnquiry findClientLoanEnquiryById (String id);

    List<ClientLoanEnquiry> findClientLoanEnquiriesByUserId(String userId);


}
