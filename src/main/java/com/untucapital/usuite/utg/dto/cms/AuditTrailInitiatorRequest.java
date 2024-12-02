package com.untucapital.usuite.utg.dto.cms;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Setter
@Getter
public class AuditTrailInitiatorRequest {
    private String initiator;
    private BigDecimal amount;
    private String fromVault;
    private String toVault;

}
