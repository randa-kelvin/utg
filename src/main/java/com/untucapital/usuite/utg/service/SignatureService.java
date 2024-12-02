package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.response.SignatureResponseDTO;
import com.untucapital.usuite.utg.model.Signature;
import com.untucapital.usuite.utg.repository.SignatureRepository;
import com.untucapital.usuite.utg.response.SignatureImgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
public class    SignatureService {
    @Autowired
    private final SignatureImgService signatureImgService;
    @Autowired
    private  final SignatureRepository signatureRepository;

    public SignatureService(SignatureImgService signatureImgService, SignatureRepository signatureRepository) {
        this.signatureImgService = signatureImgService;
        this.signatureRepository = signatureRepository;
    }

    @Transactional(value = "transactionManager")
      public List<SignatureResponseDTO> getSignature(){

        List<SignatureResponseDTO> response = new ArrayList<SignatureResponseDTO>();
        List<Signature> signatureList = signatureRepository.findAll();

        for (Signature signature : signatureList) {
            SignatureResponseDTO responseDTO = new SignatureResponseDTO();
            BeanUtils.copyProperties(signature, responseDTO);
            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public SignatureResponseDTO getSignature(String id){

        SignatureResponseDTO response = new SignatureResponseDTO();
        Optional<Signature> optionalSignature= signatureRepository.findById(id);

        if(optionalSignature.isPresent()) {
            Signature signature = optionalSignature.get();
            BeanUtils.copyProperties(signature,response);

            return response;
        }else{
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public void deleteSignature(String id){
        signatureRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public String saveSignature(MultipartFile file, String fullName, String position, String branch) {
        Signature signature = new Signature();
        String imageUrl= signatureImgService.saveFile(file);

        signature.setFullName(fullName);
        signature.setPosition(position);
        signature.setBranch(branch);
        signature.setImageUrl(imageUrl);

        signatureRepository.save(signature);

        return imageUrl;
    }

    @Transactional(value = "transactionManager")
    public void updateSignature(String id, String position, String branch){

        Signature updateSignature  = signatureRepository.getSignatureById(id);
        updateSignature.setPosition(position);
        updateSignature.setBranch(branch);

        signatureRepository.save(updateSignature);

    }
}
