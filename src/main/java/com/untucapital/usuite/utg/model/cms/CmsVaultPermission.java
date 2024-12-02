package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cms_vault_permissions")
public class CmsVaultPermission extends AbstractEntity {

    @Column(name = "userid")
    private String userid;

    @Column(name = "vault_acc_code")
    private String vault_acc_code;

    @Column(name = "vault_acc_name")
    private String vault_acc_name;

    @Column(name = "vault_acc_type")
    private String vault_acc_type;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVault_acc_code() {
        return vault_acc_code;
    }

    public void setVault_acc_code(String vault_acc_code) {
        this.vault_acc_code = vault_acc_code;
    }

    public String getVault_acc_name() {
        return vault_acc_name;
    }

    public void setVault_acc_name(String vault_acc_name) {
        this.vault_acc_name = vault_acc_name;
    }

    public String getVault_acc_type() {
        return vault_acc_type;
    }

    public void setVault_acc_type(String vault_acc_type) {
        this.vault_acc_type = vault_acc_type;
    }
}
