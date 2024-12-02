package com.untucapital.usuite.utg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int id;
    private int officeId;
    private String officeName;
    private boolean isLoanOfficer;
    private boolean isActive;
    private int userId;
    private int tellerId;
    private String firstname;
    private String lastname;
    private String displayName;
    private List<Integer> joiningDate;
}
