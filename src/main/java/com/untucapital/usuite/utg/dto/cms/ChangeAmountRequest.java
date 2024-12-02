package com.untucapital.usuite.utg.dto.cms;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */

@Getter
@Setter
public class ChangeAmountRequest {
    private Integer id;
    private BigDecimal amount;
}
