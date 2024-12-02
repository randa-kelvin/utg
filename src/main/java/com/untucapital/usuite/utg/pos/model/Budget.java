package com.untucapital.usuite.utg.pos.model;

import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Budget extends AbstractEntity {

    private String category;
    private int year;
    private Float amount;
    private int month;

    private String january;
    private String february;
    private String march;
    private String april;
    private String may;
    private String june;
    private String july;
    private String august;
    private String september;
    private String october;
    private String november;
    private String december;

}
