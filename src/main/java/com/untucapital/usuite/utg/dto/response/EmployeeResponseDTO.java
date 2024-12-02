package com.untucapital.usuite.utg.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class EmployeeResponseDTO {

    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private boolean isSelfServiceUser;
    private boolean systemDefined;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean passwordNeverExpires;
    private boolean renewPasswordOnNextLogin;
    private int officeId;
    private String officeName;
    private StaffResponseDTO staff;
    private List<EmployeeRoleResponseDTO> selectedRoles;
}
