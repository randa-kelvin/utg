package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.dto.request.SignatureUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.SignatureUploadResponseDTO;
import com.untucapital.usuite.utg.model.SignatureUpload;
import com.untucapital.usuite.utg.repository.SignatureUploadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class SignatureUploadService {

    @Autowired
    SignatureUploadRepository signatureUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(SignatureUploadRequestDTO request) {

        SignatureUpload signatureUpload = new SignatureUpload();
        BeanUtils.copyProperties(request, signatureUpload);
        signatureUploadRepository.save(signatureUpload);
    }

    @Transactional(value = "transactionManager")
    public SignatureUploadResponseDTO getSignatureFile(String userId) {

        SignatureUploadResponseDTO signatureUploadResponseDTO = new SignatureUploadResponseDTO();
        SignatureUpload signatureUpload = signatureUploadRepository.findSignatureUploadByUserId(userId);
        BeanUtils.copyProperties(signatureUpload, signatureUploadResponseDTO);

        return signatureUploadResponseDTO;
    }

    @Transactional(value = "transactionManager")
    public List<SignatureUploadResponseDTO> listAllSignatureUpload() {

        List<SignatureUploadResponseDTO> signatureUploadResponse = new ArrayList<>();
        List<SignatureUpload> signatureUploadList = signatureUploadRepository.findAll();

        for (SignatureUpload signatureUpload : signatureUploadList) {
            SignatureUploadResponseDTO responseDTO = new SignatureUploadResponseDTO();
            BeanUtils.copyProperties(signatureUpload, responseDTO);
            signatureUploadResponse.add(responseDTO);
        }

        return signatureUploadResponse;
    }

    public void updateSignatureFile(String userId, SignatureUpload signatureUpload) {

        SignatureUploadRequestDTO requestDTO = new SignatureUploadRequestDTO();
        SignatureUpload updateSignatureFile = signatureUploadRepository.findSignatureUploadByUserId(userId);
        updateSignatureFile.setFileName(signatureUpload.getFileName());
        BeanUtils.copyProperties(signatureUpload,requestDTO);
        save(requestDTO);
    }

}


