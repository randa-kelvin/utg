package com.untucapital.usuite.utg.dto.musoni.savingsaccounts;

import lombok.Data;

@Data
public class AuditData {

    private long createdOnTimestamp;
    private String createdByUsername;
    private long lastModifiedOnTimestamp;
    private String lastModifiedByUsername;
}
