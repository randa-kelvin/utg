
package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PeriodOfAnalysis")
public class PeriodOfCostAnalysis extends AbstractEntity{
    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "month_1")
    private String month1;

    @Column(name = "month_2")
    private String month2;

    @Column(name = "month_3")
    private String month3;

    @Column(name = "month_4")
    private String month4;

    @Column(name = "month_5")
    private String month5;

    @Column(name = "month_6")
    private String month6;

    @Column(name = "month_7")
    private String month7;

    @Column(name = "month_8")
    private String month8;

    @Column(name = "month_9")
    private String month9;

    @Column(name = "month_10")
    private String month10;

    @Column(name = "month_11")
    private String month11;

    @Column(name = "month_12")
    private String month12;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getMonth1() {
        return month1;
    }

    public void setMonth1(String month1) {
        this.month1 = month1;
    }

    public String getMonth2() {
        return month2;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    public String getMonth3() {
        return month3;
    }

    public void setMonth3(String month3) {
        this.month3 = month3;
    }

    public String getMonth4() {
        return month4;
    }

    public void setMonth4(String month4) {
        this.month4 = month4;
    }

    public String getMonth5() {
        return month5;
    }

    public void setMonth5(String month5) {
        this.month5 = month5;
    }

    public String getMonth6() {
        return month6;
    }

    public void setMonth6(String month6) {
        this.month6 = month6;
    }

    public String getMonth7() {
        return month7;
    }

    public void setMonth7(String month7) {
        this.month7 = month7;
    }

    public String getMonth8() {
        return month8;
    }

    public void setMonth8(String month8) {
        this.month8 = month8;
    }

    public String getMonth9() {
        return month9;
    }

    public void setMonth9(String month9) {
        this.month9 = month9;
    }

    public String getMonth10() {
        return month10;
    }

    public void setMonth10(String month10) {
        this.month10 = month10;
    }

    public String getMonth11() {
        return month11;
    }

    public void setMonth11(String month11) {
        this.month11 = month11;
    }

    public String getMonth12() {
        return month12;
    }

    public void setMonth12(String month12) {
        this.month12 = month12;
    }
}
