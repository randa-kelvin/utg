package com.untucapital.usuite.utg.controller;
import com.untucapital.usuite.utg.dto.request.SignatureUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.SignatureUploadResponseDTO;
import com.untucapital.usuite.utg.model.SignatureUpload;
import com.untucapital.usuite.utg.repository.SignatureUploadRepository;
import com.untucapital.usuite.utg.service.SignatureUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signatures")
public class SignatureUploadController {
    @Autowired
    SignatureUploadService signatureUploadService;

    @Autowired
    SignatureUploadRepository signatureUploadRepository;


    @PostMapping("/add")
    public ResponseEntity<String> addSignatureFile(@RequestBody SignatureUploadRequestDTO signatureUpload){
        signatureUploadService.save(signatureUpload);
        return new ResponseEntity<String>("Signature Uploaded",HttpStatus.OK);
    }

    @GetMapping("/getSignatures")
    public List<SignatureUploadResponseDTO> list() {
        return signatureUploadService.listAllSignatureUpload();
    }

    @GetMapping("get/{userId}")
    public  SignatureUploadResponseDTO getSignatureFile(@PathVariable String userId){
        return signatureUploadService.getSignatureFile(userId);
  }
    @PutMapping("/save/{userId}")
    public ResponseEntity<String> updateSignatureFile(@PathVariable String userId, @RequestBody SignatureUpload signatureUpload){

        signatureUploadService.updateSignatureFile(userId, signatureUpload);
        return new ResponseEntity<String>("Signature uploaded",HttpStatus.OK);

    }


}
