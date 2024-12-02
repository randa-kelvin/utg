package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.AccountBalance;
import com.untucapital.usuite.utg.dto.cms.req.VaultBalanceReq;
import com.untucapital.usuite.utg.dto.response.PostGLResponseDTO;
import com.untucapital.usuite.utg.model.cms.VaultBalances;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.service.PostGlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "postGl/", produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class PostGlController {

    private final PostGlService postGlService;

    @GetMapping("all")
    public ResponseEntity<List<PostGLResponseDTO>> getAllPostGl() {

        return ResponseEntity.ok(postGlService.getAllPostGl());
    }

//    @PostMapping("save")
//    public ResponseEntity<TransactionInfo> saveTransaction(@RequestBody TransactionInfo transactionInfo) {
//
//        try {
//            log.info("Trans Req:{}",transactionInfo);
//            TransactionInfo transactionInfo1= postGlService.savePostGlFromCMS(transactionInfo);
//            return ResponseEntity.ok(transactionInfo1);
//        }catch (Exception e) {
//            return (ResponseEntity<TransactionInfo>) ResponseEntity.badRequest();
//        }
//
//    }

    @GetMapping("getByAccountLink/{accountLink}")
    public ResponseEntity<List<PostGLResponseDTO>> getByAccountLink(@PathVariable("accountLink") Integer accountLink) {

        return ResponseEntity.ok(postGlService.getAllPostGlByAccountLink(accountLink));
    }

    @GetMapping("getByTxDate/{txDate}")
    public ResponseEntity<List<PostGLResponseDTO>> getByTxDate(@PathVariable("txDate") Date date) {

        return ResponseEntity.ok(postGlService.getAllPostGlByTxDate(date));
    }

    @PostMapping ("getVaultBalance")
    public ResponseEntity<Float> getVaultBalance(@RequestBody AccountBalance account) {

        log.info("Account:{}", account);
        return ResponseEntity.ok(postGlService.getVaultAccountBalance(account.getAccount()));
    }

    @PostMapping ("getAllPastelVaultBalances")
    public ResponseEntity<List<VaultBalances>> getAllVaultBalances(@RequestBody List<VaultBalanceReq> account) {

        log.info("Account:{}", account);
        return ResponseEntity.ok(postGlService.getAllVaultAccountBalances(account));
    }
}
