package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class DailySalesMicro extends AbstractEntity {
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "week")
    private String week;

    @Column(name = "bad_day")
    private double badDay;

    @Column(name = "normal")
    private double normal;

    @Column(name = "good_day")
    private double goodDay;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public double getBadDay() {
        return badDay;
    }

    public void setBadDay(double badDay) {
        this.badDay = badDay;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getGoodDay() {
        return goodDay;
    }

    public void setGoodDay(double goodDay) {
        this.goodDay = goodDay;
    }
}
