package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.repo.ClientRequestRepository;
import com.untucapital.usuite.utg.model.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientRequestService {
    @Autowired
    private final ClientRequestRepository clientRequestRepository;

    public ClientRequestService(ClientRequestRepository clientRequestRepository) {
        this.clientRequestRepository = clientRequestRepository;
    }
    //Add
    public void save(ClientRequest clientRequest){
        clientRequestRepository.save(clientRequest);
    }
    //Delete by id
    public void deleteById(String id){
        clientRequestRepository.deleteById(id);
    }
    //Find all by loan id
    public List<ClientRequest> findAllByLoanId(String id){
        return clientRequestRepository.findAllByLoanId(id);
    }
}
