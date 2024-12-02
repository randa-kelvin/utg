package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments extends AbstractEntity{
    @NotNull
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "comment1")
    private String comment1;

    @Column(name = "comment2")
    private String comment2;

    @Column(name = "comment3")
    private String comment3;

    @Column(name = "comment4")
    private String comment4;

    @Column(name = "comment5")
    private String comment5;

    @Column(name = "comment6")
    private String comment6;

    @Column(name = "comment7")
    private String comment7;

    @Column(name = "comment8")
    private String comment8;


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public String getComment6() {
        return comment6;
    }

    public void setComment6(String comment6) {
        this.comment6 = comment6;
    }

    public String getComment7() {
        return comment7;
    }

    public void setComment7(String comment7) {
        this.comment7 = comment7;
    }

    public String getComment8() {
        return comment8;
    }

    public void setComment8(String comment8) {
        this.comment8 = comment8;
    }
}
