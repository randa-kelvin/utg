package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class SignatureRequestDTO extends AbstractEntityDTO {

    private String fullName;

    private String position;

    private String branch;

    private String imageUrl;

    public SignatureRequestDTO() {
    }

    public SignatureRequestDTO(String fullName, String position, String branch, String imageUrl) {
        this.fullName = fullName;
        this.position = position;
        this.branch = branch;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "fullName='" + fullName + '\'' +
                ", postion='" + position + '\'' +
                ", branch='" + branch + '\'' +
                ", imageUpload='" + imageUrl + '\'' +
                '}';
    }
}
