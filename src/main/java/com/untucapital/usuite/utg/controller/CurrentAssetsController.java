package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.request.CurrentAssetRequestDTO;
import com.untucapital.usuite.utg.dto.response.CurrentAssetResponseDTO;
import com.untucapital.usuite.utg.service.CurrentAssetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("currentAssets")
public class CurrentAssetsController {
    @Autowired
    CurrentAssetsService currentAssetsService;

    private static final Logger log = LoggerFactory.getLogger(CurrentAssetsController.class);

    @GetMapping("/get/{loanId}")
    public List<CurrentAssetResponseDTO> getAllByLoanId(@PathVariable("loanId") String loanId) {
        return currentAssetsService.getCurrentAssets(loanId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        currentAssetsService.deleteCurrentAsset(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody CurrentAssetRequestDTO currentAsset) {
        currentAssetsService.saveCurrentAssets(currentAsset);
    }
}
