package com.untucapital.usuite.utg.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.dto.cms.req.VaultBalanceReq;
import com.untucapital.usuite.utg.dto.pastel.PastelTransReq;
import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.entity.res.AccountEntityResponseDTO;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import com.untucapital.usuite.utg.model.cms.VaultBalances;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.processor.PostGlProcessor;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import com.untucapital.usuite.utg.repository2.PostGlRepository;
import com.untucapital.usuite.utg.service.cms.AccountService;
import com.untucapital.usuite.utg.service.cms.VaultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class PostGlService {

    private final PostGlRepository postGlRepository;
    private final PostGlProcessor postGlProcessor;
    private final AccountService accountService;

    private final RestClient restClient;
    private final VaultRepository vaultRepository;
    private final VaultService vaultService;
    private final UserService userService;

    @Transactional(value = "pastelTransactionManager")
    public PostGLResponseDTO savePostGl(PostGLRequestDTO request) {

        PostGl postGl = new PostGl();
        PostGLResponseDTO response = new PostGLResponseDTO();

        BeanUtils.copyProperties(request, postGl);
        postGlRepository.save(postGl);
        BeanUtils.copyProperties(postGl, response);
        return response;
    }

    @Transactional(value = "pastelTransactionManager")
    public TransactionInfo savePostGlFromCMS(PastelTransReq request) throws ParseException, JsonProcessingException {

//        PastelTransReq pastelTransReq = postGlProcessor.createPastelRequest(request);
        log.info("Pastel Trans:{}", request);
        TransactionInfo trans = restClient.savePostGlTransaction(request);

        return trans;

    }

    @Transactional(value = "pastelTransactionManager")
    public TransactionInfo saveBulkPostGlFromCMS(List<PastelTransReq> request) throws JsonProcessingException {

        log.info("Request:{}", request);
        TransactionInfo trans = new TransactionInfo();
        for (PastelTransReq pastelTransReq: request){
            trans = restClient.savePostGlTransaction(pastelTransReq);
        }

        return trans;
    }

    @Transactional(value = "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGl() {

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGl> postGlList = postGlRepository.findAll();

        for (PostGl postGl1 : postGlList) {
            PostGLResponseDTO postGl = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl1, postGl);
            response.add(postGl);
        }

        return response;
    }

    @Transactional(value = "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGlByAccountLink(Integer accountLink) {

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGlResponseDTO> postGlList = postGlRepository.findByAccountLink(accountLink);
        for (PostGlResponseDTO postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;

    }

    @Transactional(value = "pastelTransactionManager")
    public List<PostGlResponseDTO> getAllPostGlByRef(String reference) {

        List<PostGlResponseDTO> response = new ArrayList<>();
        List<PostGl> postGl = postGlRepository.findByReference(reference);

        if (postGl != null) {

            for (PostGl po : postGl) {
                PostGlResponseDTO responseDTO = new PostGlResponseDTO();
                BeanUtils.copyProperties(po, responseDTO);

                response.add(responseDTO);
            }
        }
        return response;

    }

    @Transactional(value = "pastelTransactionManager")
    public List<PostGLResponseDTO> getAllPostGlByTxDate(Date txDate) {

        List<PostGLResponseDTO> response = new ArrayList<>();
        List<PostGlResponseDTO> postGlList = postGlRepository.findByTxDate(txDate);
        for (PostGlResponseDTO postGl : postGlList) {

            PostGLResponseDTO postGLResponseDTO = new PostGLResponseDTO();
            BeanUtils.copyProperties(postGl, postGLResponseDTO);

            response.add(postGLResponseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public Float getVaultAccountBalance(String account) {

        AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(account);
        Integer accountLink = accountEntity.getAccountLink();

        log.info("AccountLink:{}", accountLink);
        Float postGlBalances = postGlRepository.findAccountBalanceByAccountLink(accountLink);

        log.info("PostGlBalances:{}", postGlBalances);

        if (postGlBalances == null) {
            return 0.0F;
        }

        return postGlBalances;
    }

    @Transactional(value = "transactionManager")
    public List<VaultBalances> getAllVaultAccountBalances(List<VaultBalanceReq> request) {

        List<VaultBalances> response = new ArrayList<>();
        for (VaultBalanceReq acc : request) {

            VaultBalances vaultBalance = new VaultBalances();
            AccountEntityResponseDTO accountEntity = accountService.findAccountByAccount(acc.getAccountNumber());

            if (accountEntity ==null){
                continue;
            }
            Integer accountLink = accountEntity.getAccountLink();

            log.info("AccountLink:{}", accountLink);




            Float postGlBalances = postGlRepository.findAccountBalanceByAccountLink(accountLink);
            vaultBalance.setAccountName(acc.getAccountName());
            vaultBalance.setAccountNumber(acc.getAccountNumber());
            vaultBalance.setBalance(postGlBalances);

            response.add(vaultBalance);

            log.info("PostGlBalances:{}", response);


        }
        return response;

    }
}
