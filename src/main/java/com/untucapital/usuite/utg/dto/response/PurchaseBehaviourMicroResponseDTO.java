package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class PurchaseBehaviourMicroResponseDTO extends AbstractEntityDTO {


    private String loanId;

    private String frequency;

    private double minimum;

    private double normal;

    private double maximum;

    private double monthMin;

    private double monthAverage;

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
