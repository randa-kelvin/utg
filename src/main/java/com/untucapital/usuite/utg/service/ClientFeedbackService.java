package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.ClientFeedbackRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientFeedbackResponseDTO;
import com.untucapital.usuite.utg.model.ClientFeedback;
import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class ClientFeedbackService {

    @Autowired
    private ClientFeedbackRepository clientFeedbackRepository;

    @Transactional(value = "transactionManager")
    public ClientFeedbackResponseDTO saveClientFeedback(ClientFeedbackRequestDTO request){

        ClientFeedbackResponseDTO response = new ClientFeedbackResponseDTO();
        ClientFeedback clientFeedbackReq = new ClientFeedback();
        BeanUtils.copyProperties(request, clientFeedbackReq);
        ClientFeedback clientFeedback = clientFeedbackRepository.save(clientFeedbackReq);
        BeanUtils.copyProperties(clientFeedback, response);

        return response;

    }

    @Transactional(value = "transactionManager")
    public List<ClientFeedbackResponseDTO> getAllClientFeedback() {

        List<ClientFeedbackResponseDTO> response = new ArrayList<>();
        List<ClientFeedback> clientFeedbackList = clientFeedbackRepository.findAll();

        for(ClientFeedback clientFeedback : clientFeedbackList){
            ClientFeedbackResponseDTO responseDTO = new ClientFeedbackResponseDTO();
            BeanUtils.copyProperties(clientFeedback, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public ClientFeedbackResponseDTO getClientFeedbackById(String id) {

        ClientFeedbackResponseDTO response = new ClientFeedbackResponseDTO();
        //FIXME: findClientById method was mapped to an object of ClientEnquiry so i changed it to point to ClientFeedback
        ClientFeedback clientFeedback = clientFeedbackRepository.findClientFeedbackById(id);
        BeanUtils.copyProperties(clientFeedback, response);
        return response;
    }

    @Transactional(value = "transactionManager")
    public List<ClientFeedbackResponseDTO> getClientFeedbackByUserId(String userId) {

        List<ClientFeedbackResponseDTO> response = new ArrayList<>();
        List<ClientFeedback> clientFeedbackList =clientFeedbackRepository.findClientFeedbackByUserId(userId);

        for(ClientFeedback clientFeedback : clientFeedbackList){
            ClientFeedbackResponseDTO responseDTO = new ClientFeedbackResponseDTO();
            BeanUtils.copyProperties(clientFeedback, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }
}
