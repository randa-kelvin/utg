package com.untucapital.usuite.utg.service.treasuryManagement;

import com.untucapital.usuite.utg.model.treasuryManagement.CustomerInfo;
import com.untucapital.usuite.utg.repository.treasuryManagement.CustomerInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika
 ** @created 2024/01/25
 **************************************
 */
@Service
@RequiredArgsConstructor
public class CustomerInfoService {

    private final CustomerInfoRepository customerInfoRepository;

    //CREATE
    public CustomerInfo createCustomerInfo(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    //READ
    public CustomerInfo getCustomerInfo(Integer id) {
        return customerInfoRepository.findById(id).orElse(null);
    }

    // LIST
    public List<CustomerInfo> listCustomerInfo() {
        return customerInfoRepository.findAll();
    }

    //UPDATE
    public CustomerInfo updateCustomerInfo(CustomerInfo customerInfo) {
        CustomerInfo existingCustomerInfo = customerInfoRepository.findById(customerInfo.getId()).orElse(null);

        assert existingCustomerInfo != null;
        existingCustomerInfo.setName(customerInfo.getName());
        existingCustomerInfo.setEmail(customerInfo.getEmail());
        existingCustomerInfo.setPhoneNumber(customerInfo.getPhoneNumber());
        existingCustomerInfo.setPhoneNumberOther(customerInfo.getPhoneNumberOther());
        existingCustomerInfo.setAddress(customerInfo.getAddress());
        existingCustomerInfo.setContactPersonName(customerInfo.getContactPersonName());
        existingCustomerInfo.setContactPersonJobTitle(customerInfo.getContactPersonJobTitle());
        existingCustomerInfo.setZwlBankName(customerInfo.getZwlBankName());
        existingCustomerInfo.setZwlBankBranch(customerInfo.getZwlBankBranch());
        existingCustomerInfo.setZwlBankAccountNumber(customerInfo.getZwlBankAccountNumber());
        existingCustomerInfo.setZwlSwiftCode(customerInfo.getZwlSwiftCode());
        existingCustomerInfo.setUsdBankName(customerInfo.getUsdBankName());
        existingCustomerInfo.setUsdBankBranch(customerInfo.getUsdBankBranch());
        existingCustomerInfo.setUsdBankAccountNumber(customerInfo.getUsdBankAccountNumber());
        existingCustomerInfo.setUsdSwiftCode(customerInfo.getUsdSwiftCode());

        return customerInfoRepository.save(existingCustomerInfo);
    }

    //DELETE
    public String deleteCustomerInfo(Integer id) {
        customerInfoRepository.deleteById(id);
        return "Customer Info " + id + " deleted successfully";
    }
}
