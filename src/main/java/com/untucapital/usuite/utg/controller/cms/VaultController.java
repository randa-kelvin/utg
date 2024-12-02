package com.untucapital.usuite.utg.controller.cms;


import com.untucapital.usuite.utg.dto.cms.UpdateVaultRequest;
import com.untucapital.usuite.utg.dto.cms.VaultRequest;
import com.untucapital.usuite.utg.dto.cms.req.VaultRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.model.cms.VaultBalances;
import com.untucapital.usuite.utg.service.cms.VaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@RestController
@RequestMapping("cms/vault")
@RequiredArgsConstructor
public class VaultController {
    private final VaultService vaultService;

    //Add
    @PostMapping("/save")
    public ResponseEntity<VaultResponseDTO> addVault(@RequestBody VaultRequest vaultRequest) {
        return ResponseEntity.ok(vaultService.addVault(vaultRequest));
    }

    @PutMapping("/update")
    //Update
    public ResponseEntity<VaultResponseDTO> updateVault(@RequestBody UpdateVaultRequest vault) {
        System.out.println("Updated Vault Request"+vault.getAccount());
        return ResponseEntity.ok(vaultService.updateVault(vault));
    }

    @DeleteMapping("/delete/{vaultId}")
    //Delete
    public ResponseEntity<String> deleteVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(vaultService.deleteVault(vaultId));
    }

    @GetMapping("/get/{vaultId}")
    //Get
    public ResponseEntity<VaultResponseDTO> getVault(@PathVariable Integer vaultId) {
        return ResponseEntity.ok(vaultService.getVault(vaultId));
    }

    @GetMapping("/get/all")
    //Get All
    public ResponseEntity<List<VaultResponseDTO>> getAllVaults() {
        return ResponseEntity.ok(vaultService.getAllVaults());
    }

    @PutMapping("/update/amount/{vaultId}/{amount}")
    //Update Vault Amount
    public ResponseEntity<VaultResponseDTO> updateVaultAmount(@PathVariable Integer vaultId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(vaultService.updateVaultAmount(vaultId, amount));
    }

    @PutMapping("/update/max-amount/{vaultId}/{amount}")
    //Update Maximum Amount
    public ResponseEntity<VaultResponseDTO> updateMaxAmount(@PathVariable Integer vaultId, @PathVariable BigDecimal amount) {
        return ResponseEntity.ok(vaultService.updateVaultMaxAmount(vaultId, amount));
    }

    @GetMapping("/get/byBranchAndType/{branch}/{type}")
    public ResponseEntity<VaultResponseDTO> getVaultByBranchAndType(@PathVariable String branch, @PathVariable String type) {
        VaultResponseDTO vault = vaultService.getVaultByBranchAndType(branch, type);

        if (vault == null) {
            return ResponseEntity.noContent().build(); // Return 404 if no vaults are found
        }

        return ResponseEntity.ok(vault);
    }

    @GetMapping("/get/byType/{type}")
    public ResponseEntity<String> getVaultByType(@PathVariable String type) {
        String vault = vaultService.getVaultByType(type);

        return ResponseEntity.ok(vault);
    }


    @GetMapping("/get/byBranch/{branch}")
    public ResponseEntity<List<VaultResponseDTO>> getVaultsByBranch(@PathVariable String branch) {
        List<VaultResponseDTO> vaults = vaultService.getVaultsByBranch(branch);

        if (vaults.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no vaults are found
        }

        return ResponseEntity.ok(vaults);
    }

    //Get Vaults By Branch Id
    @GetMapping("/get/all-by-branch/{branchId}")
    public ResponseEntity<List<VaultResponseDTO>> getAllVaultsByBranch(@PathVariable String branchId) {
        return ResponseEntity.ok(vaultService.getAllVaultsByBranch(branchId));
    }

    @GetMapping("/get/total-voults-balance")
    public ResponseEntity<BigDecimal> getAllVaultsBalanceByAccount() {
        return ResponseEntity.ok(vaultService.getAllVaultsBalance());
    }

    @GetMapping("/get/vault-balance/{account}")
    public ResponseEntity<BigDecimal> getAllVaultsBalanceByAccount(@PathVariable String account) {
        return ResponseEntity.ok(vaultService.getVaultBalanceByAccount(account));
    }

    @GetMapping("/get/vault-balance")
    public ResponseEntity<List<Object[]>> getVaultBalance() {
        return ResponseEntity.ok(vaultService.getVaultBalance());
    }
}
