package com.untucapital.usuite.utg.service.cms;


import com.untucapital.usuite.utg.dto.cms.req.PettyCashPaymentsRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.PettyCashPaymentsResponseDTO;
import com.untucapital.usuite.utg.model.cms.PettyCashPayments;
import com.untucapital.usuite.utg.repository.cms.PettyCashPaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Service
@RequiredArgsConstructor
public class PettyCashPaymentsService {

    private  final PettyCashPaymentsRepository pettyCashPaymentsRepository;

    //Get All
    @Transactional(value = "transactionManager")
    public List<PettyCashPaymentsResponseDTO> getAllPettyCashPayments(){

        List<PettyCashPaymentsResponseDTO> result = new ArrayList<PettyCashPaymentsResponseDTO>();
        List<PettyCashPayments> pettyCashPaymentsList= pettyCashPaymentsRepository.findAll();

        for(PettyCashPayments payments: pettyCashPaymentsList){

            PettyCashPaymentsResponseDTO responseDTO = new PettyCashPaymentsResponseDTO();
            BeanUtils.copyProperties(payments, responseDTO);

            result.add(responseDTO);
        }

        return result;
    }
    //Get By Id
    @Transactional(value = "transactionManager")
    public PettyCashPaymentsResponseDTO getPettyCashPaymentsById(String id){

        PettyCashPaymentsResponseDTO pettyCashPaymentsResponse = new PettyCashPaymentsResponseDTO();
        PettyCashPayments pettyCashPayments = pettyCashPaymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Petty Cash Payment not found"));
        BeanUtils.copyProperties(pettyCashPayments, pettyCashPaymentsResponse);

        return pettyCashPaymentsResponse;
    }

    @Transactional(value = "transactionManager")
    public PettyCashPaymentsResponseDTO updatePettyCashPayments(String id, PettyCashPaymentsRequestDTO pettyCashPayments) {

        PettyCashPaymentsResponseDTO response = new PettyCashPaymentsResponseDTO();
        Optional<PettyCashPayments> optionalPettyCashPayments = pettyCashPaymentsRepository.findById(id);
        if (optionalPettyCashPayments.isPresent()) {
            PettyCashPayments existingPettyCashPayment = optionalPettyCashPayments.get();
//            existingPettyCashPayment.setAmount(pettyCashPayments.getAmount());
//            existingPettyCashPayment.setDate(pettyCashPayments.getDate());
//            existingPettyCashPayment.setCount(pettyCashPayments.getCount());
//            existingPettyCashPayment.setName(pettyCashPayments.getName());
            existingPettyCashPayment.setFromAccount(pettyCashPayments.getFromAccount());
//            existingPettyCashPayment.setFirstApprover(pettyCashPayments.getFirstApprover());
//            existingPettyCashPayment.setRequesitionName(pettyCashPayments.getRequesitionName());
            existingPettyCashPayment.setToAccount(pettyCashPayments.getToAccount());
            existingPettyCashPayment.setSecondApprover(pettyCashPayments.getSecondApprover());
//            existingPettyCashPayment.setPurchaseOrderNumber(pettyCashPayments.getPurchaseOrderNumber());
//            existingPettyCashPayment.setTransType(pettyCashPayments.getTransType());
            existingPettyCashPayment.setNotes(pettyCashPayments.getNotes());
            existingPettyCashPayment.setStatus(pettyCashPayments.getStatus());


            PettyCashPayments payments = pettyCashPaymentsRepository.save(existingPettyCashPayment);
            BeanUtils.copyProperties(payments, response);

            return  response;

        } else {
            return null;
        }

    }
}
