package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.dto.cms.req.TransactionPurposeRequestDTO;

import com.untucapital.usuite.utg.dto.cms.res.TransactionPurposeResponseDTO;
import com.untucapital.usuite.utg.model.cms.TransactionPurpose;
import com.untucapital.usuite.utg.repository.cms.TransactionPurposeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 5/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionPurposeService {
    private final TransactionPurposeRepository transactionPurposeRepository;

    //save
    @Transactional(value = "transactionManager")
    public TransactionPurposeResponseDTO save(TransactionPurposeRequestDTO transactionPurpose){

        TransactionPurposeResponseDTO result = new TransactionPurposeResponseDTO();
        TransactionPurpose purpose = new TransactionPurpose();
        Optional<TransactionPurpose> transactionPurpose1 = transactionPurposeRepository.findByName(transactionPurpose.getName());

        if (transactionPurpose1.isPresent()){
            throw new RuntimeException("Transaction Purpose Already Exists");
        }
        BeanUtils.copyProperties(transactionPurpose, purpose);
        TransactionPurpose transactionPurpose2 = transactionPurposeRepository.save(purpose);
        BeanUtils.copyProperties(transactionPurpose2,result);

        return result;
    }
    //update
    @Transactional(value = "transactionManager")
    public TransactionPurposeResponseDTO update(TransactionPurposeRequestDTO transactionPurpose){

        TransactionPurposeResponseDTO result = new TransactionPurposeResponseDTO();
        TransactionPurpose purpose = new TransactionPurpose();
        TransactionPurpose transactionPurpose1 = transactionPurposeRepository.getById(transactionPurpose.getId());
        if (transactionPurpose1.getId() == null){
            throw new RuntimeException("Transaction Purpose Does Not Exist");
        }

        BeanUtils.copyProperties(transactionPurpose, purpose);
        TransactionPurpose transactionPurpose2 = transactionPurposeRepository.save(purpose);
        BeanUtils.copyProperties(transactionPurpose2,result);

        return result;
    }
    //delete
    @Transactional(value = "transactionManager")
    public void delete(TransactionPurposeRequestDTO requestDTO){

        TransactionPurpose transactionPurpose = new TransactionPurpose();
        TransactionPurpose transactionPurpose1 = transactionPurposeRepository.findById(requestDTO.getId()).orElseThrow();
        if (transactionPurpose1.getId() == null){
            throw new RuntimeException("Transaction Purpose Does Not Exist");
        }

        BeanUtils.copyProperties(requestDTO, transactionPurpose);
        transactionPurposeRepository.delete(transactionPurpose);
    }
    //get all
    public List<TransactionPurposeResponseDTO> getAll(){

        List<TransactionPurposeResponseDTO> result = new ArrayList<>();
        List<TransactionPurpose> transactionPurposeList= transactionPurposeRepository.findAll();

        for(TransactionPurpose transactionPurpose : transactionPurposeList){

            TransactionPurposeResponseDTO resultResponse = new TransactionPurposeResponseDTO();
            BeanUtils.copyProperties(transactionPurpose,resultResponse);

            result.add(resultResponse);
        }

        return result;
    }

    //get by id
    public TransactionPurposeResponseDTO getById(Integer id){

        TransactionPurposeResponseDTO result = new TransactionPurposeResponseDTO();
        try {
            Optional<TransactionPurpose> transactionPurpose = transactionPurposeRepository.findById(id);

            if (transactionPurpose.isPresent()) {
                TransactionPurpose transactionPurpose1 = transactionPurpose.get();

                                BeanUtils.copyProperties(transactionPurpose1, result);

            }
        } catch (RuntimeException e) {
            log.info("Transaction Purpose Does Not Exist: {}", e.getMessage());
        }

        return result;

    }
}
