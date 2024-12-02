package com.untucapital.usuite.utg.model.treasuryManagement;

import lombok.*;

import javax.persistence.*;

/**************************************
 ** @project utg
 ** @author Takunda Jimmy Chidanika    
 ** @created 2024/01/25                   
 **************************************
 */

@Entity
@Table(name = "treasury_management_customer_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
    private String phoneNumberOther;
    private String address;

    // Contact Person
    private String contactPersonName;
    private String contactPersonJobTitle;

    // Banking Details (ZWL)
    private String zwlBankName;
    private String zwlBankBranch;
    private String zwlBankAccountNumber;
    private String zwlSwiftCode;

    // Banking Details (USD)
    private String usdBankName;
    private String usdBankBranch;
    private String usdBankAccountNumber;
    private String usdSwiftCode;
}
