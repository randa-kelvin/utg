package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.MarketLeads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketLeadsRepository extends JpaRepository<MarketLeads, String> {

    List<MarketLeads> findMarketLeadsByBranchName(String branch);

    List<MarketLeads> findMarketLeadsByLoanOfficer(String loanOfficer);
}
