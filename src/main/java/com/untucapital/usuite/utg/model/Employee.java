package com.untucapital.usuite.utg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

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
    private Staff staff;
    private List<EmployeeRole> selectedRoles;
}
