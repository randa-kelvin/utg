package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AssessmentFileUpload extends AbstractEntity{

    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "file_name")
    private String fileName;

    @NotNull
    @Column(nullable = false, name = "user_id")
    private String userId;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
