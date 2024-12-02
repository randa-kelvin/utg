package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequisitionsRequestDTO extends AbstractEntityDTO {


    private String poNumber;

    private String poName;

    private String poTotal;

    private String poCount;

    private String poStatus;

    private String notes;

    private String userId;

    private Set<String> approvers;

    private List<String> attachments;

    private String poApprover;

    private String cmsApprover;

    private String fromAccount;

    private String toAccount;

    private String teller;


}
