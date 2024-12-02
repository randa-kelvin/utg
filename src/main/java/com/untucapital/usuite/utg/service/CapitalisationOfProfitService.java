package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.CapitalisationOfProfitRequestDTO;
import com.untucapital.usuite.utg.dto.response.CapitalisationOfProfitResponseDTO;
import com.untucapital.usuite.utg.model.CapitalisationOfProfit;
import com.untucapital.usuite.utg.repository.CapitalisationOfProfitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class CapitalisationOfProfitService {
    @Autowired
    CapitalisationOfProfitRepository capitalisationOfProfitRepository;

    @Transactional(value = "transactionManager")
    public void add(CapitalisationOfProfitRequestDTO capitalisationOfProfit) {

        CapitalisationOfProfit capitalisation = new CapitalisationOfProfit();
        BeanUtils.copyProperties(capitalisationOfProfit, capitalisation);
        capitalisationOfProfitRepository.save(capitalisation);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id) {
        capitalisationOfProfitRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<CapitalisationOfProfitResponseDTO> get(String loanId) {

        List<CapitalisationOfProfitResponseDTO> result = new ArrayList<CapitalisationOfProfitResponseDTO>();
        List<CapitalisationOfProfit> capitalisationOfProfitList = capitalisationOfProfitRepository.findCapitalisationOfProfitsByLoanId(loanId);

        for (CapitalisationOfProfit capitalisationOfProfit : capitalisationOfProfitList) {
            CapitalisationOfProfitResponseDTO capitalisationOfProfitResponseDTO = new CapitalisationOfProfitResponseDTO();
            BeanUtils.copyProperties(capitalisationOfProfit, capitalisationOfProfitResponseDTO);

            result.add(capitalisationOfProfitResponseDTO);
        }

        return result;
    }
}
