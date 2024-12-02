package com.untucapital.usuite.utg.model.cms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 28/9/2023
 */


@Entity
@Table(name = "audit_trails")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "initiator")
    private String initiator;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "from_vault")
    private String fromVault;

    @Column(name = "to_vault")
    private String toVault;

    @Column(name = "initiated_at")
    private LocalDateTime initiatedAt;

    @Column(name = "first_approver")
    private String firstApprover;

    @Column(name = "first_approved_at")
    private LocalDateTime firstApprovedAt;

    @Column(name = "second_approver")
    private String secondApprover;

    @Column(name = "second_approved_at")
    private LocalDateTime secondApprovedAt;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
