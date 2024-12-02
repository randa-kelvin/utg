package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequisitionsResponseDTO extends AbstractEntityDTO {

    private String poNumber;
    private String poName;
    private String poTotal; // Use this field for total amount
    private String poCount; // Add this field for total count of transactions
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


    // New fields for approver names
    private String poApproverName;
    private String cmsApproverName;
    private String tellerName;

    // ZIMRA Tax field
    private String zimraTax;

    // Transactions
    private List<PurchaseOrderTransactionsResponseDTO> transactions;
}
