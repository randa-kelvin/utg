package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ClientFileUpload extends AbstractEntity{


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_description")
    private String fileDescription;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
