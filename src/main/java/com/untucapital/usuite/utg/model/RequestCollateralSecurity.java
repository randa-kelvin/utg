package com.untucapital.usuite.utg.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_collateral_security")
@Data
public class RequestCollateralSecurity extends AbstractEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "loan_acc")
    private String loanAcc;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "collateral_description")
    private String collateralDescription;

    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(updatable = false, name = "updated_at")
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
