package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

public class IndustryRequestDTO extends AbstractEntityDTO {

    private String name;

    private String imageUrl;

    private int code;

    private String subSector;

    private String status;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubSector() {
        return subSector;
    }

    public void setSubSector(String subSector) {
        this.subSector = subSector;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
