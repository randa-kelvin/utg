package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="financialInflow")
public class FinancialInflow extends  AbstractEntity{
    @Column(name = "loan_id", nullable = false)
    @NotNull
    private String loanId;

    @Column(name = "inflow_name", nullable = false)
    @NotNull
    private String inflowName;

    @Column(name = "month1")
    private double month1;

    @Column(name = "month2")
    private double month2;

    @Column(name = "month3")
    private double month3;

    @Column(name = "month4")
    private double month4;

    @Column(name = "month5")
    private double month5;

    @Column(name = "month6")
    private double month6;

    @Column(name = "month7")
    private double month7;

    @Column(name = "month8")
    private double month8;

    @Column(name = "month9")
    private double month9;

    @Column(name = "month10")
    private double month10;

    @Column(name = "month11")
    private double month11;

    @Column(name = "month12")
    private double month12;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getInflowName() {
        return inflowName;
    }

    public void setInflowName(String inflowName) {
        this.inflowName = inflowName;
    }

    public double getMonth1() {
        return month1;
    }

    public void setMonth1(double month1) {
        this.month1 = month1;
    }

    public double getMonth2() {
        return month2;
    }

    public void setMonth2(double month2) {
        this.month2 = month2;
    }

    public double getMonth3() {
        return month3;
    }

    public void setMonth3(double month3) {
        this.month3 = month3;
    }

    public double getMonth4() {
        return month4;
    }

    public void setMonth4(double month4) {
        this.month4 = month4;
    }

    public double getMonth5() {
        return month5;
    }

    public void setMonth5(double month5) {
        this.month5 = month5;
    }

    public double getMonth6() {
        return month6;
    }

    public void setMonth6(double month6) {
        this.month6 = month6;
    }

    public double getMonth7() {
        return month7;
    }

    public void setMonth7(double month7) {
        this.month7 = month7;
    }

    public double getMonth8() {
        return month8;
    }

    public void setMonth8(double month8) {
        this.month8 = month8;
    }

    public double getMonth9() {
        return month9;
    }

    public void setMonth9(double month9) {
        this.month9 = month9;
    }

    public double getMonth10() {
        return month10;
    }

    public void setMonth10(double month10) {
        this.month10 = month10;
    }

    public double getMonth11() {
        return month11;
    }

    public void setMonth11(double month11) {
        this.month11 = month11;
    }

    public double getMonth12() {
        return month12;
    }

    public void setMonth12(double month12) {
        this.month12 = month12;
    }
}
