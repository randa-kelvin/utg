package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_directorships")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Directorship extends AbstractEntity {

    @Column(name = "company_name")
    @JsonProperty(value = "company_name")
    private String companyName;

    @Column(name = "trade_name")
    @JsonProperty(value = "trade_name")
    private String tradeName;

    @Column(name = "registration_number")
    @JsonProperty(value = "registration_number")
    private String registrationNumber;

    @Column(name = "dof")
    private String dof;

    @Column(name = "status")
    private String status;

    @Column(name = "date_searched")
    @JsonProperty(value = "date_searched")
    private String dateSearched;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDof() {
        return dof;
    }

    public void setDof(String dof) {
        this.dof = dof;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(String dateSearched) {
        this.dateSearched = dateSearched;
    }
}
