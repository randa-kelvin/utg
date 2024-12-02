package com.untucapital.usuite.utg.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Getter
@Setter
public class UpdateVaultRequest {
    private Integer id;
    private String account;
    private String type;
    private String name;
    private String branchId;
}
