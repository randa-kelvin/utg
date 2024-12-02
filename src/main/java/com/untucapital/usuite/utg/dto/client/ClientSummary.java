package com.untucapital.usuite.utg.dto.client;

import lombok.Data;

@Data
public class ClientSummary {
    private int id;
    private String status;
    private String activationDate;
    private String firstname;
    private String lastname;
    private String displayName;
    private String mobileNo;
    private String dateOfBirth;
    private String gender;
    private String officeName;
}
