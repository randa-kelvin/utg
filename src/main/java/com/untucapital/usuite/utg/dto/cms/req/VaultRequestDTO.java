package com.untucapital.usuite.utg.dto.cms.req;

import com.untucapital.usuite.utg.model.Branches;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaultRequestDTO {

    private Integer id;

    private String account;

    private String type;

    private String name;

    private BigDecimal maxAmount;

    private BigDecimal currentAmount;

    private Integer accountLink;

    private Branches branch;
    private String code;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getAccountLink() {
        return accountLink;
    }

    public void setAccountLink(Integer accountLink) {
        this.accountLink = accountLink;
    }

//    public Branches getBranch() {
//        return branch;
//    }
//
//    public void setBranch(Branches branch) {
//        this.branch = branch;
//    }

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
