package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.cms.CmsVaultPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmsVaultPermissionRepository extends JpaRepository<CmsVaultPermission, String> {

    Optional<CmsVaultPermission> getCmsVaultPermissionById(String id);

    List<CmsVaultPermission> findCmsVaultPermissionsByUserid(String userId);

    @Query("SELECT v FROM CmsVaultPermission v WHERE v.userid = :userid AND v.vault_acc_type = :vaultAccType")
    Optional<CmsVaultPermission> findByUseridAndVaultAccType(@Param("userid") String userid, @Param("vaultAccType") String vaultAccType);

    @Query("SELECT v FROM CmsVaultPermission v WHERE v.vault_acc_type = :vaultAccType")
    Optional<CmsVaultPermission> findByVaultAccType(@Param("vaultAccType") String vaultAccType);




    List<CmsVaultPermission> findAllByUserid(String id);

//    List<CmsVaultPermission> getCmsVaultPermissionsByVault_acc_code (String vault_acc);

}
