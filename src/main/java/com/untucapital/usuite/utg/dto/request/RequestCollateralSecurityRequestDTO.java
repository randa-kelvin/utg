package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

import java.time.LocalDateTime;


public class RequestCollateralSecurityRequestDTO extends AbstractEntityDTO {


    private String phoneNumber;

    private String loanAcc;

    private String clientName;

    private String collateralDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLoanAcc() {
        return loanAcc;
    }

    public void setLoanAcc(String loanAcc) {
        this.loanAcc = loanAcc;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCollateralDescription() {
        return collateralDescription;
    }

    public void setCollateralDescription(String collateralDescription) {
        this.collateralDescription = collateralDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
