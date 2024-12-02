package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CommentsOnFixedAssetsMicro")
public class CommentsOnFixedAssets extends AbstractEntity {

    @Column(name = "loan_id", nullable = false)
    @NotNull
    private String loanId;

    @Column(name = "description", nullable = false)
    @NotNull
    private String description;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
