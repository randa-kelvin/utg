package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.pos.dto.POSSupplierDto;
import com.untucapital.usuite.utg.pos.model.POSSupplier;
import com.untucapital.usuite.utg.pos.repository.SupplierRepository;
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
 * @created 5/9/2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    //Save Supplier
    @Transactional(value = "transactionManager")
    public POSSupplierDto saveSupplier(POSSupplierDto request) {

        POSSupplierDto response = new POSSupplierDto();
        POSSupplier posSupplier =new POSSupplier();

        BeanUtils.copyProperties(request, posSupplier);
        Optional<POSSupplier> existingSupplier = supplierRepository.findByNameAndPhone(posSupplier.getName(), posSupplier.getPhone());

        if(existingSupplier.isPresent()){
            BeanUtils.copyProperties(existingSupplier.get(), response);
            throw new RuntimeException("Supplier already exists, with the following details: " + response.toString());
        }

        POSSupplier posSupplier1 = supplierRepository.save(posSupplier);
        BeanUtils.copyProperties(posSupplier1, response);

        return response;
    }

    //Get Supplier By Id
    @Transactional(value = "transactionManager")
    public POSSupplierDto getSupplierById(Integer id) {

        POSSupplier supplier = supplierRepository.findById(id).orElse(null);
        POSSupplierDto response = new POSSupplierDto();
        BeanUtils.copyProperties(supplier, response);

        return response;
    }

    //Get All Suppliers
    @Transactional(value = "transactionManager")
    public List<POSSupplierDto> getAllSuppliers() {

        List<POSSupplierDto> posSupplierDtoList = new ArrayList<POSSupplierDto>();
        List<POSSupplier> posSupplierList= supplierRepository.findAll();

        for(POSSupplier posSupplier : posSupplierList){
            POSSupplierDto posSupplierDto = new POSSupplierDto();
            BeanUtils.copyProperties(posSupplier, posSupplierDto);
            posSupplierDtoList.add(posSupplierDto);
        }
        return posSupplierDtoList;
    }

    //Get Supplier By Id
    @Transactional(value = "transactionManager")
    public List<POSSupplierDto> getApprovedSupplier(String status ) {

        List<POSSupplierDto> response = new ArrayList<POSSupplierDto>();
        List<POSSupplier> posSupplierList =supplierRepository.findByStatus(ApprovalStatus.valueOf(status));

        for (POSSupplier posSupplier : posSupplierList) {
            POSSupplierDto posSupplierDto = new POSSupplierDto();
            BeanUtils.copyProperties(posSupplier, posSupplierDto);
            response.add(posSupplierDto);
        }

        return response;
    }

    //Update Supplier
    @Transactional(value = "transactionManager")
    public POSSupplierDto updateSupplier(POSSupplierDto posSupplier) {
        POSSupplier existingSupplier = supplierRepository.findById(posSupplier.getId()).orElse(null);

        assert existingSupplier != null;
        existingSupplier.setName(posSupplier.getName());
        existingSupplier.setAddress(posSupplier.getAddress());
        existingSupplier.setPhone(posSupplier.getPhone());
        existingSupplier.setContactPerson(posSupplier.getContactPerson());
        existingSupplier.setComment(posSupplier.getComment());
        existingSupplier.setTaxClearance(posSupplier.getTaxClearance());
        POSSupplier supplier = supplierRepository.save(existingSupplier);
        POSSupplierDto posSupplierDto = new POSSupplierDto();
        BeanUtils.copyProperties(supplier, posSupplierDto);

        return posSupplierDto;
    }
    //Delete Supplier
    @Transactional(value = "transactionManager")
    public POSSupplierDto deleteSupplier(Integer id) {

        POSSupplierDto response = new POSSupplierDto();
        POSSupplier supplier = supplierRepository.findById(id).orElse(null);
        supplierRepository.deleteById(id);
        BeanUtils.copyProperties(supplier, response);
        return response;
    }

}
