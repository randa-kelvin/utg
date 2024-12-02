package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PurchaseBehaviourMicro extends AbstractEntity {

    @Column(nullable = false, name = "loan_id")
    private String loanId;
    @Column(name = "frequency")
    private String frequency;

    @Column(name = "minimum")
    private double minimum;

    @Column(name = "normal")
    private double normal;

    @Column(name = "maximum")
    private double maximum;

    @Column(name = "month_min")
    private double monthMin;

    @Column(name = "month_average")
    private double monthAverage;

    @Column(name = "month_max")
    private double monthMax;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public double getMonthMin() {
        return monthMin;
    }

    public void setMonthMin(double monthMin) {
        this.monthMin = monthMin;
    }

    public double getMonthAverage() {
        return monthAverage;
    }

    public void setMonthAverage(double monthAverage) {
        this.monthAverage = monthAverage;
    }

    public double getMonthMax() {
        return monthMax;
    }

    public void setMonthMax(double monthMax) {
        this.monthMax = monthMax;
    }
}
