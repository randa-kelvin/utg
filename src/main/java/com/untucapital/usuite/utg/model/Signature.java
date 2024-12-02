package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="employee_signature")
public class Signature extends AbstractEntity {

    @Column(nullable = false, name="full_name")
    private String fullName;
    @Column(nullable = false, name = "position")
    private String position;
    @Column(nullable = false, name = "branch")
    private String branch;
    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    public Signature() {
    }

    public Signature(String fullName, String position, String branch, String imageUrl) {
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
