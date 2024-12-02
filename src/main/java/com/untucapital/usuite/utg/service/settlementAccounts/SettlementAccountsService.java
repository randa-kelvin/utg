package com.untucapital.usuite.utg.service.settlementAccounts;

import com.untucapital.usuite.utg.exception.ClientNotFoundException;
import com.untucapital.usuite.utg.exception.TokenExpiryException;
import com.untucapital.usuite.utg.model.settlements.SettlementAccountsTokens;
import com.untucapital.usuite.utg.repository.settlementsAccounts.SettlementAccountsTokensRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SettlementAccountsService {

    private final SettlementAccountsTokensRepository settlementAccountsTokensRepository;

    public boolean verifySettlementAccountsToken(String clientId, String token){

        Optional<SettlementAccountsTokens> settlementAccountsTokens = settlementAccountsTokensRepository.findByClientIdAndToken(clientId, token);

        if (settlementAccountsTokens.isPresent()){
                SettlementAccountsTokens tokens = settlementAccountsTokens.get();

                if (LocalDateTime.now().isBefore(ChronoLocalDateTime.from(tokens.getExpirationDate()))){
                    return true;
                }else {
                    throw new TokenExpiryException("Your Confirmation Code has expired");
            }

        } else {
            throw new ClientNotFoundException("Invalid Confirmation code!");
        }
    }
}
