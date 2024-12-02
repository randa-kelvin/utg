package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.enums.RoleType;


public class RoleRequestDTO extends AbstractEntityDTO {

    private RoleType name;

    private String description;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
