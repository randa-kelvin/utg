package com.untucapital.usuite.utg.dto.po;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;


/**
 * @author KelvinR
 * @created 07/02/2024
 */

@Getter
@Setter
@Builder
public class RequisitionsDTO extends AbstractEntityDTO {
    private String poNumber;
    private String poName;
    private String poTotal;
    private String poCount;
    private String poStatus;
    private String notes;
    private String userId;
    private List<String> approvers;
    private List<String> attachments;
    private String poApprover;
    private String cmsApprover;
    private String fromAccount;
    private String toAccount;
    private String teller;
}
