package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailySalesMicroResponseDTO extends AbstractEntity {

    private String loanId;
    private String week;
    private double badDay;
    private double normal;
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
