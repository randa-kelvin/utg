package com.untucapital.usuite.utg.model.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditData {
    private long createdOnTimestamp;
    private String createdByUsername;
    private long lastModifiedOnTimestamp;
    private String lastModifiedByUsername;

}
