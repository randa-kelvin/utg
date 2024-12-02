package com.untucapital.usuite.utg.repository.settlementsAccounts;

import com.untucapital.usuite.utg.model.settlements.SettlementAccountsTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlementAccountsTokensRepository extends JpaRepository<SettlementAccountsTokens, String> {
    Optional<SettlementAccountsTokens> findByClientIdAndToken(String clientId, String token);


}
