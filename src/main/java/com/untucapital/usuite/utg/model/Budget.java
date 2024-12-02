//package com.untucapital.usuite.utg.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
///**
// * @author tjchidanika
// * @created 5/9/2023
// */
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class Budget {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String category;
//    private int year;
//    private BigDecimal january;
//    private BigDecimal february;
//    private BigDecimal march;
//    private BigDecimal april;
//    private BigDecimal may;
//    private BigDecimal june;
//    private BigDecimal july;
//    private BigDecimal august;
//    private BigDecimal september;
//    private BigDecimal october;
//    private BigDecimal november;
//    private BigDecimal december;
//
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedAt;
//
//}
