package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.dto.request.ClientsDatasetsRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientsDatasetsResponseDTO;
import com.untucapital.usuite.utg.model.ClientsDatasets;
import com.untucapital.usuite.utg.repository.ClientDatasetsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class ClientDatasetsService {

    @Autowired
    private ClientDatasetsRepository clientDatasetsRepository;

    @Transactional(value = "transactionManager")
    public ClientsDatasetsResponseDTO saveclientsDatasets(ClientsDatasetsRequestDTO requestDTO){

        ClientsDatasetsResponseDTO responseDTO = new ClientsDatasetsResponseDTO();
        ClientsDatasets clientsDatasets = new ClientsDatasets();
        BeanUtils.copyProperties(requestDTO, clientsDatasets);
        ClientsDatasets savedDatasets = clientDatasetsRepository.save(clientsDatasets);
        BeanUtils.copyProperties(savedDatasets, responseDTO);

        return responseDTO;
    }

    @Transactional(value = "transactionManager")
    public List<ClientsDatasetsResponseDTO> getAllclientsDatasets() {

        List<ClientsDatasetsResponseDTO> responseDTOList = new ArrayList<>();
        List<ClientsDatasets> clientsDatasetsList = clientDatasetsRepository.findAll();

        for(ClientsDatasets clientsDatasets : clientsDatasetsList){

            ClientsDatasetsResponseDTO responseDTO = new ClientsDatasetsResponseDTO();
            BeanUtils.copyProperties(clientsDatasets, responseDTO);

            responseDTOList.add(responseDTO);

        }

        return responseDTOList;
    }

    @Transactional(value = "transactionManager")
    public List<ClientsDatasetsResponseDTO> getClientsByBranch( String branch){

        List<ClientsDatasetsResponseDTO> response = new ArrayList<ClientsDatasetsResponseDTO>();
        List<ClientsDatasets> clientsDatasetsList = clientDatasetsRepository.findByBranch(branch);

        for(ClientsDatasets clientsDatasets : clientsDatasetsList){
            ClientsDatasetsResponseDTO responseDTO = new ClientsDatasetsResponseDTO();
            BeanUtils.copyProperties(clientsDatasets,responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public ClientsDatasetsResponseDTO getclientsDatasetsById(Long id) {

        ClientsDatasetsResponseDTO response = new ClientsDatasetsResponseDTO();
        ClientsDatasets clientsDatasets= clientDatasetsRepository.findClientsDatasetsById(id);
        BeanUtils.copyProperties(clientsDatasets, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public void updateclientsDatasets(Long id, ClientsDatasetsRequestDTO clientsDatasets){

        ClientsDatasets updatedclientsDatasets = clientDatasetsRepository.findClientsDatasetsById(id);
        updatedclientsDatasets.setFirstName(clientsDatasets.getFirstName());
        updatedclientsDatasets.setLastName(clientsDatasets.getLastName());
        updatedclientsDatasets.setBusinessSector(clientsDatasets.getBusinessSector());
        updatedclientsDatasets.setPhoneNumber(clientsDatasets.getPhoneNumber());
        updatedclientsDatasets.setBranch(clientsDatasets.getBranch());
        updatedclientsDatasets.setNationalId(clientsDatasets.getNationalId());
        updatedclientsDatasets.setHomeAddress(clientsDatasets.getHomeAddress());
        updatedclientsDatasets.setCreditRating(clientsDatasets.getCreditRating());
        updatedclientsDatasets.setLoanAmount(clientsDatasets.getLoanAmount());
        updatedclientsDatasets.setLoanProduct(clientsDatasets.getLoanProduct());
        updatedclientsDatasets.setOrigin(clientsDatasets.getOrigin());
        updatedclientsDatasets.setGender(clientsDatasets.getGender());
        updatedclientsDatasets.setDateOfBirth(clientsDatasets.getDateOfBirth());

        clientDatasetsRepository.save(updatedclientsDatasets);
    }


}
