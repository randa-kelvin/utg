package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Setter
@Getter
@Entity
@Table(name = "fcb_response")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response extends AbstractEntity {

    @Column(name = "code")
    private Integer code;

    @Column(name = "individual")
    private String individual;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    @Column(name = "report")
    @JsonProperty(value = "Report")
    private List<Report> report;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Address> addresses;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Search> searches;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Directorship> directorships;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Active> active;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Inactive> inactive;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Exposure> exposures;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Incomes> incomes;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    private List<Employer> employer;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE})
    @Column(name = "additional_info")
    @JsonProperty(value = "additional_info")
    private List<AdditionalInfo> additionalInfo;


}
