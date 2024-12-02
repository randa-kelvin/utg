package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.dto.response.DatabaseFileResponseDTO;
import com.untucapital.usuite.utg.payload.Response;
import com.untucapital.usuite.utg.service.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "credit_application")
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("description") String description, @RequestParam("userId") String userId, @RequestParam("loanId") String loanId) {
    	DatabaseFileResponseDTO fileName = fileStorageService.storeFile(file, description, userId, loanId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .path(fileName.getFileDescription())
                .path(fileName.getUserId())
                .path(fileName.getLoanId())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), fileName.getFileDescription(), fileName.getUserId(), fileName.getLoanId() ,file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("description") String description, @RequestParam("userId") String userId, @RequestParam("loanId") String loanId) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, description, userId, loanId))
                .collect(Collectors.toList());
    }
}
