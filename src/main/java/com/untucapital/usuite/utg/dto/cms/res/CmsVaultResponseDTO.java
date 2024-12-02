package com.untucapital.usuite.utg.dto.cms.res;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Data
@RequiredArgsConstructor
public class CmsVaultResponseDTO extends AbstractEntityDTO {

    private String account;
    private String name;
    private String description;

}
