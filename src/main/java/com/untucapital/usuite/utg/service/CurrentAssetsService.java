package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.CurrentAssetRequestDTO;
import com.untucapital.usuite.utg.dto.response.CurrentAssetResponseDTO;
import com.untucapital.usuite.utg.model.CurrentAsset;
import com.untucapital.usuite.utg.repository.CurrentAssetsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CurrentAssetsService {
    @Autowired
    CurrentAssetsRepository currentAssetsRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveCurrentAssets(CurrentAssetRequestDTO request){
        CurrentAsset currentAsset = new CurrentAsset();
        BeanUtils.copyProperties(request, currentAsset);
        currentAssetsRepository.save(currentAsset);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<CurrentAssetResponseDTO> getCurrentAssets(String loanId){

        List<CurrentAssetResponseDTO> response = new ArrayList<>();
        List<CurrentAsset> currentAssetList = currentAssetsRepository.findCurrentAssetByLoanId(loanId);

        for(CurrentAsset currentAsset : currentAssetList){

            CurrentAssetResponseDTO responseDTO = new CurrentAssetResponseDTO();
            BeanUtils.copyProperties(currentAsset, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteCurrentAsset(String id){
        currentAssetsRepository.deleteById(id);
    }
}
