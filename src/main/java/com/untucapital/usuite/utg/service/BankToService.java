package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.BankToRequestDTO;
import com.untucapital.usuite.utg.dto.response.BankToResponseDTO;
import com.untucapital.usuite.utg.model.BankTo;
import com.untucapital.usuite.utg.repository.BankToRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@javax.transaction.Transactional
public class BankToService {
    @Autowired
    private final BankToRepository bankToRepository;

    public BankToService(BankToRepository bankToRepository) {
        this.bankToRepository = bankToRepository;
    }

    @Transactional(value = "transactionManager")
    public List<BankTo> listAllBankTo() {
        return bankToRepository.findAll();
    }

    @Transactional(value = "transactionManager")
    public void saveBankTo(BankToRequestDTO request) {
        BankTo bankTo = new BankTo();
        BeanUtils.copyProperties(request, bankTo);
        bankToRepository.save(bankTo);
    }

    //Get List of Business Units by Id
    @Transactional(value = "transactionManager")
    public List<BankToResponseDTO> listBankToByLoanId(String id, String bank){
        List<BankToResponseDTO> responseDTOList = new ArrayList<>();

        List<BankTo> bankToList = bankToRepository.findBankToByLoanIdAndBankOrderByMonthAsc(id, bank);

        for(BankTo bankTo : bankToList){
            BankToResponseDTO responseDTO = new BankToResponseDTO();
            BeanUtils.copyProperties(bankTo, responseDTO);

            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }

    @Transactional(value = "transactionManager")
    public void deleteById(String id) {
        bankToRepository.deleteById(id);
    }
}
