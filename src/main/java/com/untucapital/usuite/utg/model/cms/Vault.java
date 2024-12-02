package com.untucapital.usuite.utg.model.cms;

import com.untucapital.usuite.utg.model.Branches;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 27/9/2023
 */

@Entity
@Table(name = "vaults")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "account")
    private String account;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "current_amount")
    private BigDecimal currentAmount;

    @Column(name = "account_link")
    private Integer accountLink;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Branches branch;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
