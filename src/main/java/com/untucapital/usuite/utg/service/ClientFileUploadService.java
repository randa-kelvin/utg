package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.ClientFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.ClientFileUploadResponseDTO;
import com.untucapital.usuite.utg.model.ClientFileUpload;
import com.untucapital.usuite.utg.repository.ClientFileUploadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class ClientFileUploadService {

    @Autowired
    ClientFileUploadRepository clientFileUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(ClientFileUploadRequestDTO request){

        ClientFileUpload clientFileUpload = new ClientFileUpload();
        BeanUtils.copyProperties(request, clientFileUpload);
        clientFileUploadRepository.save(clientFileUpload);
    }

    @Transactional(value = "transactionManager")
    public List<ClientFileUploadResponseDTO> getClientFileUpload(String userId){

        List<ClientFileUploadResponseDTO> response = new ArrayList<>();
        List<ClientFileUpload> clientFileUploadList =clientFileUploadRepository.findClientFileUploadByUserId(userId);

        for(ClientFileUpload clientFileUpload :clientFileUploadList){
            ClientFileUploadResponseDTO responseDTO = new ClientFileUploadResponseDTO();
            BeanUtils.copyProperties(clientFileUpload, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }
}
