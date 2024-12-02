package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AverageDailySalesMicro extends AbstractEntity {
    @Column(name = "loan_id", nullable = false)
    private String loanId;

    @Column(name = "monday")
    private double monday;

    @Column(name = "tuesday")
    private double tuesday;

    @Column(name = "wednesday")
    private double wednesday;

    @Column(name = "thursday")
    private double thursday;

    @Column(name = "friday")
    private double friday;

    @Column(name = "saturday")
    private double saturday;

    @Column(name = "sunday")
    private double sunday;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getMonday() {
        return monday;
    }

    public void setMonday(double monday) {
        this.monday = monday;
    }

    public double getTuesday() {
        return tuesday;
    }

    public void setTuesday(double tuesday) {
        this.tuesday = tuesday;
    }

    public double getWednesday() {
        return wednesday;
    }

    public void setWednesday(double wednesday) {
        this.wednesday = wednesday;
    }

    public double getThursday() {
        return thursday;
    }

    public void setThursday(double thursday) {
        this.thursday = thursday;
    }

    public double getFriday() {
        return friday;
    }

    public void setFriday(double friday) {
        this.friday = friday;
    }

    public double getSaturday() {
        return saturday;
    }

    public void setSaturday(double saturday) {
        this.saturday = saturday;
    }

    public double getSunday() {
        return sunday;
    }

    public void setSunday(double sunday) {
        this.sunday = sunday;
    }
}
