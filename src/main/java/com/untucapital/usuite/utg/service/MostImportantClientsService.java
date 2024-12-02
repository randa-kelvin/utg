package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MostImportantClientsRequestDTO;
import com.untucapital.usuite.utg.dto.response.MostImportantClientsResponseDTO;
import com.untucapital.usuite.utg.model.MostImportantClients;
import com.untucapital.usuite.utg.repository.MostImportantClientsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class MostImportantClientsService {
    @Autowired
    MostImportantClientsRepository mostImportantClientsRepository;

    @Transactional(value = "transactionManager")
    public void saveMostImportantClients(MostImportantClientsRequestDTO request){

        MostImportantClients mostImportantClients = new MostImportantClients();

        BeanUtils.copyProperties(request, mostImportantClients);
        mostImportantClientsRepository.save(mostImportantClients);
    }

    @Transactional(value = "transactionManager")
    public List<MostImportantClientsResponseDTO> getMostImportantClientsByLoanId(String loanId){

        List<MostImportantClientsResponseDTO> mostImportantClientsResponseDTOList = new ArrayList<MostImportantClientsResponseDTO>();
        List<MostImportantClients> mostImportantClients =mostImportantClientsRepository.findByLoanId(loanId);

        for(MostImportantClients client : mostImportantClients){

            MostImportantClientsResponseDTO responseDTO = new MostImportantClientsResponseDTO();
            BeanUtils.copyProperties(client, responseDTO);

            mostImportantClientsResponseDTOList.add(responseDTO);
        }

        return mostImportantClientsResponseDTOList;
    }

    @Transactional(value = "transactionManager")
    public void deleteMostImportantCliennts(String id){
        mostImportantClientsRepository.deleteById(id);
    }
}

