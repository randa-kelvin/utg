package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
public class Stock extends AbstractEntity{

    @NotNull
    @Column(nullable = false, name ="loan_id")
    private String loanId;

    @NotNull
    @Column(nullable = false)
    private String stockName;

    @Column(name="quantity")
    private double quantity;

    @Column(name="purchase_price")
    private double purchasePrice;
    @Column(name="sale_price")
    private double salePrice;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @author tjchidanika
     * @created 5/9/2023
     */

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Budget {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "category")
        private String category;

        @Column(name = "year")
        private int year;

        @Column(name = "january")
        private BigDecimal january;

        @Column(name = "february")
        private BigDecimal february;

        @Column(name = "march")
        private BigDecimal march;

        @Column(name = "april")
        private BigDecimal april;

        @Column(name = "may")
        private BigDecimal may;

        @Column(name = "june")
        private BigDecimal june;

        @Column(name = "july")
        private BigDecimal july;

        @Column(name = "august")
        private BigDecimal august;

        @Column(name = "september")
        private BigDecimal september;

        @Column(name = "october")
        private BigDecimal october;

        @Column(name = "november")
        private BigDecimal november;

        @Column(name = "december")
        private BigDecimal december;

        @Column(name = "created_at")
        @CreationTimestamp
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        @UpdateTimestamp
        private LocalDateTime updatedAt;

    }
}
