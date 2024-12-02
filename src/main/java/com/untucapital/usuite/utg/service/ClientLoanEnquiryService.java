package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.ClientLoanEnquiryRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientLoanEnquiryResponseDTO;
import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClientLoanEnquiryService {

    @Autowired
    private ClientLoanEnquiryRepository clientLoanEnquiryRepository;

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public ClientLoanEnquiryResponseDTO saveClientLoanEnquiry(ClientLoanEnquiryRequestDTO requestDTO){

        ClientLoanEnquiryResponseDTO responseDTO = new ClientLoanEnquiryResponseDTO();
        ClientLoanEnquiry clientLoanEnquiry = new ClientLoanEnquiry();
        BeanUtils.copyProperties(requestDTO, clientLoanEnquiry);
        ClientLoanEnquiry clientLoanEnquiry1= clientLoanEnquiryRepository.save(clientLoanEnquiry);
        BeanUtils.copyProperties(clientLoanEnquiry1, responseDTO);

        return responseDTO;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<ClientLoanEnquiryResponseDTO> getAllClientLoanEnquiries() {

        List<ClientLoanEnquiryResponseDTO> response = new ArrayList<ClientLoanEnquiryResponseDTO>();
        List<ClientLoanEnquiry> clientLoanEnquiryList = clientLoanEnquiryRepository.findAll();

        for(ClientLoanEnquiry client: clientLoanEnquiryList){
            ClientLoanEnquiryResponseDTO responseDTO = new ClientLoanEnquiryResponseDTO();
            BeanUtils.copyProperties(client, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public ClientLoanEnquiryResponseDTO getClientLoanEnquiryById( String id) {

        ClientLoanEnquiryResponseDTO response = new ClientLoanEnquiryResponseDTO();
        ClientLoanEnquiry clientLoanEnquiry=clientLoanEnquiryRepository.findClientLoanEnquiryById(id);
        BeanUtils.copyProperties(clientLoanEnquiry,response);

        return response;
    }

    //build get clientLoan by ID REST API
    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<ClientLoanEnquiryResponseDTO> getClientLoanEnquiryByUserId(@PathVariable("userId") String userId) {

        List<ClientLoanEnquiryResponseDTO> response = new ArrayList<ClientLoanEnquiryResponseDTO>();
        List<ClientLoanEnquiry> clientLoanEnquiryList =clientLoanEnquiryRepository.findClientLoanEnquiriesByUserId(userId);

        for(ClientLoanEnquiry client: clientLoanEnquiryList){
            ClientLoanEnquiryResponseDTO responseDTO = new ClientLoanEnquiryResponseDTO();
            BeanUtils.copyProperties(client, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }
}
