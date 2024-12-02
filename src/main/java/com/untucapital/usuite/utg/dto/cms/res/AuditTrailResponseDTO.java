package com.untucapital.usuite.utg.dto.cms.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuditTrailResponseDTO {

    private Integer id;
    private String initiator;
    private BigDecimal amount;
    private String fromVault;
    private String toVault;
    private LocalDateTime initiatedAt;
    private String firstApprover;
    private LocalDateTime firstApprovedAt;
    private String secondApprover;
    private LocalDateTime secondApprovedAt;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
