package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.entity.res.AccountEntityResponseDTO;
import com.untucapital.usuite.utg.entity.AccountEntity;
import com.untucapital.usuite.utg.repository2.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService {

    private final AccountsRepository accountsRepository;

    @Transactional(value = "transactionManager")
    public AccountEntityResponseDTO findAccountByAccount(String account) {

        AccountEntityResponseDTO response = new AccountEntityResponseDTO();
        AccountEntity entity = new AccountEntity();
        Optional<AccountEntity> accountEntity = accountsRepository.findByAccount(account);
        log.info("accountEntity {}", accountEntity);
        if(accountEntity.isPresent()) {
            log.info("Account : {}", accountEntity.toString());
            entity = accountEntity.get();

         }else{
//            Optional<AccountEntity> account1 = accountsRepository.findByAccount("8422/000/HRE/FCA/MV");
//            entity= account1.get();
            return null;
        }

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Transactional(value = "transactionManager")
    public AccountEntityResponseDTO findAccounts(String account) {

        AccountEntityResponseDTO response = new AccountEntityResponseDTO();
        AccountEntity entity = new AccountEntity();
        Optional<AccountEntity> accountEntity = accountsRepository.findByAccount(account);
        log.info("accountEntity {}", accountEntity);
        if(accountEntity.isPresent()) {
            log.info("Account : {}", accountEntity.toString());
            entity = accountEntity.get();

        }else{
//            Optional<AccountEntity> account1 = accountsRepository.findByAccount("8422/000/HRE/FCA/MV");
//            entity= account1.get();
            return null;
        }

        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
