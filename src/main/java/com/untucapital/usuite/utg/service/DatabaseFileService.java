package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.response.DatabaseFileResponseDTO;
import com.untucapital.usuite.utg.exception.FileNotFoundException;
import com.untucapital.usuite.utg.exception.FileStorageException;
import com.untucapital.usuite.utg.model.DatabaseFile;
import com.untucapital.usuite.utg.repository.DatabaseFileRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    @Transactional(value = "transactionManager")
    public DatabaseFileResponseDTO storeFile(MultipartFile file, String fileDescription, String userId, String loanId) {

        DatabaseFile d = new DatabaseFile();
        d.setFileDescription(fileDescription);
        d.setUserId(userId);
        d.setLoanId(loanId);
//        dbFileRepository.save(d);

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName,file.getContentType(),fileDescription, userId, loanId,file.getBytes());
            DatabaseFileResponseDTO response = new DatabaseFileResponseDTO();

            DatabaseFile databaseFile =  dbFileRepository.save(dbFile);
            BeanUtils.copyProperties(databaseFile, response);

            return response;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Transactional(value = "transactionManager")
    public DatabaseFileResponseDTO getFile(String fileId) {

        DatabaseFileResponseDTO response = new DatabaseFileResponseDTO();

        DatabaseFile dbFile = dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
        BeanUtils.copyProperties(dbFile, response);

        return  response;
    }

    @Transactional(value = "transactionManager")
    public DatabaseFileResponseDTO getFileById(String fileID) {

        DatabaseFileResponseDTO response = new DatabaseFileResponseDTO();
        DatabaseFile databaseFile= dbFileRepository.getFileById(fileID);
        BeanUtils.copyProperties(databaseFile, response);

        return response;
    }

    // select files excluding appraisal ************
    @Transactional(value = "transactionManager")
    public List<DatabaseFileResponseDTO> getUploadFilesByUserId(String userId, String appraisal, String selfie, String nationalId) {

        List<DatabaseFileResponseDTO> response = new ArrayList<DatabaseFileResponseDTO>();
       List<DatabaseFile> databaseFileList = dbFileRepository.findByUserIdAndFileDescriptionNotContainsAndFileDescriptionNotContainsAndFileDescriptionNotContains(userId, appraisal, selfie, nationalId);

       for(DatabaseFile databaseFile : databaseFileList) {

           DatabaseFileResponseDTO responseDTO = new DatabaseFileResponseDTO();
           BeanUtils.copyProperties(databaseFile, responseDTO);

           response.add(responseDTO);
       }

       return response;
    }

//    @Query(value = "SELECT * FROM files WHERE fielDescription = 'selfie' OR fileDescription = 'nationalId' ")

    // select selfie and nationalId files
    @Transactional(value = "transactionManager")
    public List<DatabaseFileResponseDTO> getLoanFiles(String userId, String loanId,  String appraisal, String assessmentFile ) {

        List<DatabaseFileResponseDTO> response = new ArrayList<DatabaseFileResponseDTO>();
       List<DatabaseFile> databaseFileList =dbFileRepository.findByUserIdAndLoanIdAndFileDescriptionNotContainsAndFileDescriptionNotContains(userId, loanId, appraisal, assessmentFile);

        for(DatabaseFile databaseFile : databaseFileList) {

            DatabaseFileResponseDTO responseDTO = new DatabaseFileResponseDTO();
            BeanUtils.copyProperties(databaseFile, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    // Excel appraisal.. select using userid and description = appraisal
    @Transactional(value = "transactionManager")
    public List<DatabaseFileResponseDTO> getUploadFilesByAppraisal(@PathVariable("userId") String userId, @PathVariable("loanId") String loanId, @PathVariable("fileDescription") String fileDescription) {

        List<DatabaseFileResponseDTO> response = new ArrayList<DatabaseFileResponseDTO>();
       List<DatabaseFile> databaseFileList = dbFileRepository.findByUserIdAndLoanIdAndFileDescription(userId, loanId, fileDescription);

        for(DatabaseFile databaseFile : databaseFileList) {

            DatabaseFileResponseDTO responseDTO = new DatabaseFileResponseDTO();
            BeanUtils.copyProperties(databaseFile, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }
}
