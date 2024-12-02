package com.untucapital.usuite.utg.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class EmployeeRoleRequestDTO {

    private int id;
    private String name;
    private String description;
    private boolean disabled;
    private boolean systemDefined;
}
