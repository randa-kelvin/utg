package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.MostImportantSuppliersRequestDTO;
import com.untucapital.usuite.utg.dto.response.MostImportantSuppliersResponseDTO;
import com.untucapital.usuite.utg.model.MostImportantSuppliers;
import com.untucapital.usuite.utg.repository.MostImportantSuppliersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@javax.transaction.Transactional
public class MostImportantSuppliersService {
    @Autowired
    MostImportantSuppliersRepository mostImportantSuppliersRepository;

    @Transactional(value = "transactionManager")
    public void saveMostImportantSuppliers(MostImportantSuppliersRequestDTO request){

        MostImportantSuppliers mostImportantSuppliers = new MostImportantSuppliers();
        BeanUtils.copyProperties(request,mostImportantSuppliers);
        mostImportantSuppliersRepository.save(mostImportantSuppliers);
    }

    @Transactional(value = "transactionManager")
    public List<MostImportantSuppliersResponseDTO> getMostImportantSuppliersByLoanId(String loanId){

        List<MostImportantSuppliersResponseDTO> response = new ArrayList<>();
        List<MostImportantSuppliers> mostImportantSuppliersList = mostImportantSuppliersRepository.findByLoanId(loanId);

        for(MostImportantSuppliers mostImportantSuppliers : mostImportantSuppliersList){

            MostImportantSuppliersResponseDTO responseDTO = new MostImportantSuppliersResponseDTO();
            BeanUtils.copyProperties(mostImportantSuppliers,responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public void deleteMostImportantSuppliers(String id){
        mostImportantSuppliersRepository.deleteById(id);
    }
}
