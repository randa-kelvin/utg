package com.untucapital.usuite.utg.dto.cms.res;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Getter
@Setter
public class AuthorisationResponseDTO extends AbstractEntityDTO {

    private String branchId;
    private String authLevel;
    private String userId;

}
