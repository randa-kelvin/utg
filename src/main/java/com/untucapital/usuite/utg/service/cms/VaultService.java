package com.untucapital.usuite.utg.service.cms;


import com.untucapital.usuite.utg.dto.cms.UpdateVaultRequest;
import com.untucapital.usuite.utg.dto.cms.VaultRequest;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.cms.VaultBalances;
import com.untucapital.usuite.utg.repository.BranchRepository;
import com.untucapital.usuite.utg.repository.cms.VaultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class VaultService {

    private final VaultRepository vaultRepository;
    private final BranchRepository branchRepository;


    //Add
    @Transactional(value = "transactionManager")
    public VaultResponseDTO addVault(VaultRequest vaultRequest) {

        VaultResponseDTO vaultResponse = new VaultResponseDTO();
        Branches branch = branchRepository.findById(vaultRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        log.info("Branch : {}", branch);
        Vault vault = Vault.builder()
                .account(vaultRequest.getAccount())
                .type(vaultRequest.getType())
                .name(vaultRequest.getName())
                .code(vaultRequest.getCode())
                .branch(branch)
                .build();
        Vault vault1= vaultRepository.save(vault);
        BeanUtils.copyProperties(vault1, vaultResponse);

        return vaultResponse;
    }

    //Update
    @Transactional(value = "transactionManager")
    public VaultResponseDTO updateVault(UpdateVaultRequest vault) {

        VaultResponseDTO vaultResponse = new VaultResponseDTO();
        Vault existingVault = vaultRepository.findById(vault.getId())
                .orElseThrow(() -> new RuntimeException("Vault not found"));


        if (vault.getAccount() != null && !vault.getAccount().equals(existingVault.getAccount())) {
            existingVault.setAccount(vault.getAccount());
        }

        if (vault.getName() != null && !vault.getName().equals(existingVault.getName())) {
            existingVault.setName(vault.getName());
        }
        if (vault.getCode() != null && !vault.getCode().equals(existingVault.getCode())) {
            existingVault.setCode(vault.getCode());
        }

        if (vault.getType() != null && !vault.getType().equals(existingVault.getType())) {
            existingVault.setType(vault.getType());
        }

        if (vault.getBranchId() != null && !vault.getBranchId().equals(existingVault.getBranch().getId())) {
            Branches branch = branchRepository.findById(vault.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            existingVault.setBranch(branch);
        }

        Vault vault1 = vaultRepository.save(existingVault);
        BeanUtils.copyProperties(vault1, vaultResponse);

        return vaultResponse;
    }

    //Delete
    @Transactional(value = "transactionManager")
    public String deleteVault(Integer vaultId) {

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));
        vaultRepository.delete(existingVault);

        return String.format("Vault with id %d deleted successfully", vaultId);
    }

    //Get
    @Transactional(value = "transactionManager")
    public VaultResponseDTO getVault(Integer vaultId) {

        VaultResponseDTO vaultResponseDTO = new VaultResponseDTO();

        Vault vault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        BeanUtils.copyProperties(vault,vaultResponseDTO);

        return vaultResponseDTO;
    }

    //Get All
    @Transactional(value = "transactionManager")
    public List<VaultResponseDTO> getAllVaults() {

        List<VaultResponseDTO> response = new ArrayList<>();
        List<Vault> vaultList = vaultRepository.findAll();

        for(Vault vault : vaultList){

            VaultResponseDTO vaultResponseDTO = new VaultResponseDTO();
            BeanUtils.copyProperties(vault, vaultResponseDTO);

            response.add(vaultResponseDTO);
        }

        return response;
    }

    //Update Vault Amount
    public VaultResponseDTO updateVaultAmount(Integer vaultId, BigDecimal amount) {

        VaultResponseDTO response = new VaultResponseDTO();

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));
        existingVault.setCurrentAmount(existingVault.getCurrentAmount().add(amount));
        Vault vault = vaultRepository.save(existingVault);
        BeanUtils.copyProperties(vault, response);

        return response;
    }

    //Update Maximum Amount
    @Transactional(value = "transactionManager")
    public VaultResponseDTO updateVaultMaxAmount(Integer vaultId, BigDecimal amount) {

        VaultResponseDTO response = new VaultResponseDTO();

        Vault existingVault = vaultRepository.findById(vaultId)
                .orElseThrow(() -> new RuntimeException("Vault not found"));

        existingVault.setMaxAmount(amount);

        Vault vault =vaultRepository.save(existingVault);
        BeanUtils.copyProperties(vault, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public VaultResponseDTO getVaultByBranchAndType(String branch, String type) {

        log.info("Branch and Type:{}", branch + type);

        VaultResponseDTO response = new VaultResponseDTO();

        Optional<Vault> vault = vaultRepository.findByNameContainingAndType(branch, type);

        //FIXME: this is a bypass for testing
        if (vault.isEmpty()) {
//            Vault vault1 = new Vault();
//            vault1.setAccount("8422/000/HRE/FCA/MV");
//            BeanUtils.copyProperties(vault1, response);
            return response;
        }

        Vault vault1= vault.get();
        BeanUtils.copyProperties(vault1, response);

        return response;
    }

    @Transactional(value = "transactionManager")
    public String getVaultByType(String type) {
        log.info("Type:{}", type);
        Optional<Vault> vault =vaultRepository.findByType(type);
        if (vault.isPresent()){
            Vault vault1 = vault.get();
            return vault1.getAccount();
        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public List<VaultResponseDTO> getVaultsByBranch(String branch) {

        List<VaultResponseDTO> responseList = new ArrayList<VaultResponseDTO>();
        List<Vault> vaultList= vaultRepository.findVaultByBranch_BranchName(branch);

        for(Vault vault : vaultList){
            VaultResponseDTO response = new VaultResponseDTO();
            BeanUtils.copyProperties( vault, response);

            responseList.add(response);
        }

        return responseList;
    }

    //Vaults By Branch
    @Transactional(value = "transactionManager")
    public List<VaultResponseDTO> getAllVaultsByBranch(String branchId) {

        List<VaultResponseDTO> response = new ArrayList<>();

        Branches branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        List<Vault> vaultList= vaultRepository.findByBranch(branch);

        for(Vault vault : vaultList){
            VaultResponseDTO responseDTO = new VaultResponseDTO();
            BeanUtils.copyProperties( vault, responseDTO);

            response.add(responseDTO);
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public BigDecimal getAllVaultsBalance() {

        BigDecimal vaultBalance = vaultRepository.findTotalAccountBalances();

        return vaultBalance;
    }

    @Transactional(value = "transactionManager")
    public VaultResponseDTO updateVaultBalance(BigDecimal balance, String account) {

        VaultResponseDTO vaultResponse = new VaultResponseDTO();
        Optional<Vault> vault = vaultRepository.findByAccount(account);

        if(vault.isPresent()) {
            Vault current = vault.get();
            current.setCurrentAmount(balance);
            vaultRepository.save(current);
            BeanUtils.copyProperties(current, vaultResponse);
            return vaultResponse;
        }else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public BigDecimal getVaultBalanceByAccount(String account) {

        BigDecimal vaultBalance = vaultRepository.findAccountBalanceByAccount(account);
//if (vaultBalance.equals(0)){
//
//}
        return vaultBalance;
    }

    @Transactional(value = "transactionManager")
    public List<Object[]> getVaultBalance() {

        List<Object[]> vaultBalance = vaultRepository.findAccountBalances();

        return vaultBalance;
    }

}
