package com.untucapital.usuite.utg.pos.model;

import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    @Enumerated
    private ApprovalStatus status;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<POSSuplierFileUploads> supplierFiles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "bsn_type")
    private String bsnType;

    @Column(name = "bsn_reg_no")
    private String bsnRegNo;

    @Column(name = "banking_info")
    private String bankingInfo;

    @Column(name = "tax_id_no")
    private String taxIdNo;

    @Column(name = "tax_clearance")
    private String taxClearance;

}
