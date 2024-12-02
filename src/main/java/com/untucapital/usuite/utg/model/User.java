package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.cms.CmsUser;
import com.untucapital.usuite.utg.model.po.PoUser;
import com.untucapital.usuite.utg.model.tms.TmsUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "contact_detail_id"})
})
@Data
public class User extends AbstractEntity {

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_token")
    private String resetPasswordToken;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @NotNull
    @JoinColumn(name = "contact_detail_id", nullable = false)
    private ContactDetail contactDetail;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "cms_user_id")
    private CmsUser cmsUser;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "po_user_id")
    private PoUser poUser;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @JoinColumn(name = "tms_user_id")
    private TmsUser tmsUser;

    @NotNull
    @Column(nullable = false)
    private boolean active;

    @NotNull
    @Column(nullable = false)
    private boolean verified;

    private String branch;

    @Column(name = "credit_commit_group")
    private String creditCommitGroup;

    @Column(name = "dirt_of_birth")
    private String dirtOfBirth;

    @Column(name = "marital_status")
    private String maritalStatus;

    private String Gender;

    private String City;

    private String suburb;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "musoni_client_id")
    private String musoniClientId;

    @Column(name = "national_id")
    private String nationalId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
