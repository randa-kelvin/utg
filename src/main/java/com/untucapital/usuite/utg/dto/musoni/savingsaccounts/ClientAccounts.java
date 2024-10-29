package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class ClientAccounts extends Response {

    private String loanId;

    private String clientId;

    private String phoneNumber;

    private String postMaturityFee;

    private String settlementAccount;
}
