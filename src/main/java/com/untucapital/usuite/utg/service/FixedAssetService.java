package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.FixedAssetRequestDTO;
import com.untucapital.usuite.utg.dto.response.FixedAssetResponseDTO;
import com.untucapital.usuite.utg.model.FixedAsset;
import com.untucapital.usuite.utg.repository.FixedAssetsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class FixedAssetService {
    @Autowired
    FixedAssetsRepository fixedAssetsRepository;

    @Transactional(value = "transactionManager")
    public void saveFixedAssets(FixedAssetRequestDTO requestDTO){

        FixedAsset fixedAsset = new FixedAsset();
        BeanUtils.copyProperties(requestDTO,fixedAsset);

        fixedAssetsRepository.save(fixedAsset);
    }

    @Transactional(value = "transactionManager")
    public List<FixedAssetResponseDTO> getFixedAssets(String loanId){

        List<FixedAssetResponseDTO> response = new ArrayList<>();
        List<FixedAsset> fixedAssetList = fixedAssetsRepository.findFixedAssetByLoanId(loanId);

        for(FixedAsset fixedAsset: fixedAssetList){

            FixedAssetResponseDTO responseDTO = new FixedAssetResponseDTO();
            BeanUtils.copyProperties(fixedAsset, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteFixedAsset(String id){
        fixedAssetsRepository.deleteById(id);
    }
}
