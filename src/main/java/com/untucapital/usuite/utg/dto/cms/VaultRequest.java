package com.untucapital.usuite.utg.dto.cms;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Getter
@Setter
public class VaultRequest {
    private String account;
    private String name;
    private String code;
    private String type;
    private String branchId;
}
