package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cms_approver")
public class CmsApprover extends AbstractEntity {

    @Column(name = "vault")
    private String vault;

    @Column(name = "initiator")
    private String initiator;

    @Column(name = "first_approver")
    private String first_approver;

    @Column(name = "second_approver")
    private String second_approver;


}
