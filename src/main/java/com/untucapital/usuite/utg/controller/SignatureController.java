package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.response.SignatureResponseDTO;
import com.untucapital.usuite.utg.response.SignatureResponse;
import com.untucapital.usuite.utg.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "signature")
public class SignatureController {

    @Autowired
    private final SignatureService signatureService;
    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }


    @PostMapping("/save")
    public ResponseEntity<SignatureResponse> saveSignature(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("fullName") String fullName,
            @RequestParam("position") String position,
            @RequestParam("branch") String branch
    ){
        String fileName = signatureService.saveSignature(file, fullName, position, branch);
        SignatureResponse signatureResponse = new SignatureResponse(fileName);
        return ResponseEntity.ok().body(signatureResponse);
    }

    @GetMapping("/signatures")
    public List<SignatureResponseDTO> getSignature(){
        return  signatureService.getSignature();
    }

    @GetMapping("/signature/{id}")
    public SignatureResponseDTO getSignature(@PathVariable("id") String id){
        return signatureService.getSignature(id);
    }

    @DeleteMapping("/delete/{id}")
    public  void deleteSignature(@PathVariable("id") String id){
        signatureService.deleteSignature(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSignature(
            @PathVariable("id") String id,
            @RequestParam("position") String position,
            @RequestParam("branch") String branch){

        signatureService.updateSignature(id, position, branch);

        return  new ResponseEntity<String>("Signature Updated", HttpStatus.OK);
    }


}
