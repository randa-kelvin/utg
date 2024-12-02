package com.untucapital.usuite.utg.dto.cms.res;

import com.untucapital.usuite.utg.model.Branches;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author panashe rutimhu
 * @created 6/11/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VaultResponseDTO {


    private Integer id;

    private String account;

    private String type;

    private String name;

    private String code;

    private BigDecimal maxAmount;

    private BigDecimal currentAmount;

    private Integer accountLink;

    private Branches branch;

    @UpdateTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
