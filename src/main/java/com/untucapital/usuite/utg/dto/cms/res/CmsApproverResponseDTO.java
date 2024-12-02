package com.untucapital.usuite.utg.dto.cms.res;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@RequiredArgsConstructor
public class CmsApproverResponseDTO extends AbstractEntityDTO {

    private String vault;
    private String initiator;
    private String first_approver;
    private String second_approver;


}
