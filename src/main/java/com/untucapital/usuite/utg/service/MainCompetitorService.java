package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MainCompetitorRequestDTO;
import com.untucapital.usuite.utg.dto.response.MainCompetitorResponseDTO;
import com.untucapital.usuite.utg.model.MainCompetitor;
import com.untucapital.usuite.utg.repository.MainCompetitorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MainCompetitorService {
    @Autowired
    MainCompetitorRepository mainCompetitorRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void saveMainCompetitor(MainCompetitorRequestDTO request){

        MainCompetitor mainCompetitor = new MainCompetitor();
        BeanUtils.copyProperties(request, mainCompetitor);
        mainCompetitorRepository.save(mainCompetitor);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<MainCompetitorResponseDTO> getMainCompetitorByLoanId(String loanId){

        List<MainCompetitorResponseDTO> result = new ArrayList<>();
        List<MainCompetitor> mainCompetitorList = mainCompetitorRepository.findByLoanId(loanId);

        for(MainCompetitor mainCompetitor : mainCompetitorList){
            MainCompetitorResponseDTO responseDTO= new MainCompetitorResponseDTO();
            BeanUtils.copyProperties(mainCompetitor,responseDTO);

            result.add(responseDTO);
        }
        return result;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public void deleteMainCompetitor(String id){
        mainCompetitorRepository.deleteById(id);
    }
}
