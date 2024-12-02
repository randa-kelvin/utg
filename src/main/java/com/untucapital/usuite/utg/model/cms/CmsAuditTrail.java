package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */


@Entity
@Table(name = "audit_trail")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CmsAuditTrail extends AbstractEntity {

    @Column(name = "user")
    private String user;

    @Column(name = "action")
    private String action;

    @Column(name = "auth_level")
    private String auth_level;

    @Column(name = "from_account")
    private String from_account;

    @Column(name = "to_account")
    private String to_account;

    @Column(name = "branch")
    private String branch;

    @Column(name = "role")
    private String role;



}
