package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class SettlementAccountResponse extends Response {

    private String clientId;

    private String phoneNumber;
}
