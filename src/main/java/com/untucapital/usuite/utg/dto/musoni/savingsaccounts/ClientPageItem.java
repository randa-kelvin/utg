package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientPageItem {

    String id;
    String accountNo;
    String status;
    String mobileNo;
    LocalDate dateOfBirth;

}
