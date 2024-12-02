package com.untucapital.usuite.utg.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RejectionReason {

    private boolean active;
    private boolean isDefault;
}
