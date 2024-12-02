package com.untucapital.usuite.utg.model.po;

import com.sun.istack.NotNull;
import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "requisitions")
public class Requisitions extends AbstractEntity {

    @NotNull
    @Column(nullable = false, name = "po_number")
    private String poNumber;

    @NotNull
    @Column(nullable = false, name = "po_name")
    private String poName;

    @Column(name = "po_total")
    private String poTotal;

    @Column(name = "po_count")
    private String poCount;

    @NotNull
    @Column(nullable = false, name = "po_status")
    private String poStatus;

    @Column(name = "notes")
    private String notes;

    @Column(name = "user_id")
    private String userId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> approvers;
    @ElementCollection
    @BatchSize(size = 10)
    private List<String> attachments;

    @Column(name = "po_approver")
    private String poApprover;

    @Column(name = "cms_approver")
    private String cmsApprover;

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "teller")
    private String teller;

}
