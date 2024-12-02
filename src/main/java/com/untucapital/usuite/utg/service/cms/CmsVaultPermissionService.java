package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.dto.cms.req.CmsVaultPermissionRequestDTO;
import com.untucapital.usuite.utg.dto.cms.res.CmsVaultPermissionResponseDTO;
import com.untucapital.usuite.utg.dto.cms.res.VaultResponseDTO;
import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import com.untucapital.usuite.utg.model.cms.Vault;
import com.untucapital.usuite.utg.repository.cms.CmsVaultPermissionRepository;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@javax.transaction.Transactional
@Slf4j
public class CmsVaultPermissionService {

    @Autowired
    private CmsVaultPermissionRepository cmsVaultPermissionRepository;

    @Autowired
    private VaultService vaultService;

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermissionResponseDTO> getAllCmsVaultPermissions() {

        List<CmsVaultPermissionResponseDTO> response = new ArrayList<>();
        List<CmsVaultPermission> cmsVaultPermissionList = cmsVaultPermissionRepository.findAll();

        for(CmsVaultPermission cmsVaultPermission: cmsVaultPermissionList){

            CmsVaultPermissionResponseDTO responseDTO = new CmsVaultPermissionResponseDTO();
            BeanUtils.copyProperties(cmsVaultPermission, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }

    @Transactional(value = "transactionManager")
    public void saveCmsVaultPermission(CmsVaultPermissionRequestDTO request) {

        CmsVaultPermission cmsVaultPermission = new CmsVaultPermission();
        BeanUtils.copyProperties(request, cmsVaultPermission);
        cmsVaultPermissionRepository.save(cmsVaultPermission);
    }

    @Transactional(value = "transactionManager")
    public CmsVaultPermissionResponseDTO getCmsVaultPermissionById(String id) {

        CmsVaultPermissionResponseDTO response = new CmsVaultPermissionResponseDTO();
        Optional<CmsVaultPermission> cmsVaultPermissionOptional =cmsVaultPermissionRepository.findById(id);

        if(cmsVaultPermissionOptional.isPresent()){

            CmsVaultPermission cmsVaultPermission = cmsVaultPermissionOptional.get();
            BeanUtils.copyProperties(cmsVaultPermission,response);

        } else {
            return null;
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public CmsVaultPermissionResponseDTO getCmsVaultPermissionByPoNumber(String id) {

        CmsVaultPermissionResponseDTO response = new CmsVaultPermissionResponseDTO();
        Optional<CmsVaultPermission> cmsVaultPermissionOptional= cmsVaultPermissionRepository.getCmsVaultPermissionById(id);

        if(cmsVaultPermissionOptional.isPresent()){

            CmsVaultPermission cmsVaultPermission = cmsVaultPermissionOptional.get();
            BeanUtils.copyProperties(cmsVaultPermission,response);

        } else {
            return null;
        }

        return response;
    }

    @Transactional(value = "transactionManager")
    public List<CmsVaultPermissionResponseDTO> getCmsVaultPermissionByUserId(String userId) {

        List<CmsVaultPermissionResponseDTO> response = new ArrayList<>();
        List<CmsVaultPermission> cmsVaultPermissionList= cmsVaultPermissionRepository.findCmsVaultPermissionsByUserid(userId);


        for(CmsVaultPermission cmsVaultPermission: cmsVaultPermissionList){

            CmsVaultPermissionResponseDTO responseDTO = new CmsVaultPermissionResponseDTO();
            BeanUtils.copyProperties(cmsVaultPermission, responseDTO);

            response.add(responseDTO);
        }
        return response;
    }


    @Transactional(value = "transactionManager")
    public void deleteCmsVaultPermission(String id) {
        cmsVaultPermissionRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public String getCmsVaultPermissionByUserIdAndVaultType(String userId, String vault_acc_type) {

        log.info("userid and vaultType : {}", vault_acc_type );
        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionRepository.findByUseridAndVaultAccType(userId, vault_acc_type);
        if (cmsVaultPermission.isPresent()){

            CmsVaultPermission cmsVaultPermission1 =  cmsVaultPermission.get();

            VaultResponseDTO vault = vaultService.getVault(Integer.valueOf(cmsVaultPermission1.getVault_acc_code()));

            return  vault.getAccount();

        } else {
            return null;
        }
    }

    @Transactional(value = "transactionManager")
    public String getCmsVaultPermissionByVaultType(String vault_acc_type) {

        log.info("userid and vaultType : {}", vault_acc_type );
        Optional<CmsVaultPermission> cmsVaultPermission = cmsVaultPermissionRepository.findByVaultAccType(vault_acc_type);
        if (cmsVaultPermission.isPresent()){

            CmsVaultPermission cmsVaultPermission1 =  cmsVaultPermission.get();

            VaultResponseDTO vault = vaultService.getVault(Integer.valueOf(cmsVaultPermission1.getVault_acc_code()));

            return  vault.getAccount();

        } else {
            return null;
        }
    }

//    public List<CmsVaultPermission> getCmsVaultPermissionByVaultAccCode(String vaultAccCode) {
//        return cmsVaultPermissionRepository.getCmsVaultPermissionsByVault_acc_code(vaultAccCode);
//    }
}
