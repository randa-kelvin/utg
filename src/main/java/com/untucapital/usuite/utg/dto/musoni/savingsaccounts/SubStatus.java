package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class SubStatus {
    private int id;
    private String code;
    private String value;
    private boolean none;
    private boolean inactive;
    private boolean dormant;
    private boolean escheat;
    private boolean writeoff;

}
