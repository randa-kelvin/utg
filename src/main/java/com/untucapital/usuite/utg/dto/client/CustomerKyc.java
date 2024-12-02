package com.untucapital.usuite.utg.dto.client;

import lombok.Data;

@Data
public class CustomerKyc {

    private String nationalId;
    private String maritalStatus;
    private String streetNumber;
    private String streetName;
    private String suburbName;
    private String city;


}
