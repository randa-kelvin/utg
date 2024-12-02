package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;
import org.springframework.context.annotation.Profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cms_vault")

public class CmsVault extends AbstractEntity {

    @Column(name = "account")
    private String account;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;



}
