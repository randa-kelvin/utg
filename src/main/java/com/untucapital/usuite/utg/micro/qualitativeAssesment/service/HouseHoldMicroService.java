package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.HouseHoldAssetsMicroRepository;
import com.untucapital.usuite.utg.model.HouseHoldAssetsMicro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseHoldMicroService {
    @Autowired
    private final HouseHoldAssetsMicroRepository houseHoldAssetsMicroRepository;

    public HouseHoldMicroService(HouseHoldAssetsMicroRepository houseHoldAssetsMicroRepository) {
        this.houseHoldAssetsMicroRepository = houseHoldAssetsMicroRepository;
    }

    public void save(HouseHoldAssetsMicro houseHoldAssetsMicro) {
        houseHoldAssetsMicroRepository.save(houseHoldAssetsMicro);
    }

    public void deleteById(String id) {
        houseHoldAssetsMicroRepository.deleteById(id);
    }

    public List<HouseHoldAssetsMicro> findAllByLoanId(String id) {
        return houseHoldAssetsMicroRepository.findAllByLoanId(id);
    }
}
