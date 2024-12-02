package com.untucapital.usuite.utg.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class AppraisalFileUpload extends  AbstractEntity{

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_description")
    private String fileDescription;

    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    private String userId;

    @JoinColumn(name = "loan_id")
    private String loanId;


}
