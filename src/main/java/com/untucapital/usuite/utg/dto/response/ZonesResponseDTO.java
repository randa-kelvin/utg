package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;

public class ZonesResponseDTO extends AbstractEntityDTO {


    private String name;

    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
