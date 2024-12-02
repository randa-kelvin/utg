package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cms_users")
public class CmsUser extends AbstractEntity {

    @Column(name = "role")
    private String role;

    @Column(name = "teller_vault")
    private String teller_vault;

    @Column(name = "branch_vault")
    private String branch_vault;

    @Column(name = "external_vault")
    private String external_vault;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeller_vault() {
        return teller_vault;
    }

    public void setTeller_vault(String teller_vault) {
        this.teller_vault = teller_vault;
    }

    public String getBranch_vault() {
        return branch_vault;
    }

    public void setBranch_vault(String branch_vault) {
        this.branch_vault = branch_vault;
    }

    public String getExternal_vault() {
        return external_vault;
    }

    public void setExternal_vault(String external_vault) {
        this.external_vault = external_vault;
    }
}
