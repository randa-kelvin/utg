package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "business")
public class Business extends AbstractEntity{

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "type_of_business", nullable = false)
    private String typeOfBusiness;

    @Column(name = "business_address", nullable = false)
    private String businessAddress;

    @Column(name = "business_start_date", nullable = false)
    private String businessStartDate;

    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;



    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public void setTypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

