package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SeasonalityOfSalesMicro extends AbstractEntity {
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "january")
    private String january;

    @Column(name = "february")
    private String february;

    @Column(name = "march")
    private String march;

    @Column(name = "april")
    private String april;

    @Column(name = "may")
    private String may;

    @Column(name = "june")
    private String june;

    @Column(name = "july")
    private String july;

    @Column(name = "august")
    private String august;

    @Column(name = "september")
    private String september;

    @Column(name = "october")
    private String october;

    @Column(name = "november")
    private String november;

    @Column(name = "december")
    private String december;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getJanuary() {
        return january;
    }

    public void setJanuary(String january) {
        this.january = january;
    }

    public String getFebruary() {
        return february;
    }

    public void setFebruary(String february) {
        this.february = february;
    }

    public String getMarch() {
        return march;
    }

    public void setMarch(String march) {
        this.march = march;
    }

    public String getApril() {
        return april;
    }

    public void setApril(String april) {
        this.april = april;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJune() {
        return june;
    }

    public void setJune(String june) {
        this.june = june;
    }

    public String getJuly() {
        return july;
    }

    public void setJuly(String july) {
        this.july = july;
    }

    public String getAugust() {
        return august;
    }

    public void setAugust(String august) {
        this.august = august;
    }

    public String getSeptember() {
        return september;
    }

    public void setSeptember(String september) {
        this.september = september;
    }

    public String getOctober() {
        return october;
    }

    public void setOctober(String october) {
        this.october = october;
    }

    public String getNovember() {
        return november;
    }

    public void setNovember(String november) {
        this.november = november;
    }

    public String getDecember() {
        return december;
    }

    public void setDecember(String december) {
        this.december = december;
    }
}
