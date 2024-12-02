package com.untucapital.usuite.utg.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EmployeeRoleResponseDTO {

    private int id;
    private String name;
    private String description;
    private boolean disabled;
    private boolean systemDefined;
}
