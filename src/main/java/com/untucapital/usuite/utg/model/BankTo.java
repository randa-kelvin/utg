package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bankTo")
public class BankTo extends AbstractEntity{
    @NotNull
    @Column(nullable = false, name = "month")
    private String month;

    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @NotNull
    @Column(nullable = false, name = "bank")
    private String bank;

    @Column(name = "day1")
    private double day1;

    @Column(name = "day2")
    private double day2;

    @Column(name = "day3")
    private double day3;

    @Column(name = "day4")
    private double day4;

    @Column(name = "day5")
    private double day5;

    @Column(name = "day6")
    private double day6;

    @Column(name = "day7")
    private double day7;

    @Column(name = "day8")
    private double day8;

    @Column(name = "day9")
    private double day9;

    @Column(name = "day10")
    private double day10;

    @Column(name = "day11")
    private double day11;

    @Column(name = "day12")
    private double day12;

    @Column(name = "day13")
    private double day13;

    @Column(name = "day14")
    private double day14;

    @Column(name = "day15")
    private double day15;

    @Column(name = "day16")
    private double day16;

    @Column(name = "day17")
    private double day17;

    @Column(name = "day18")
    private double day18;

    @Column(name = "day19")
    private double day19;

    @Column(name = "day20")
    private double day20;

    @Column(name = "day21")
    private double day21;

    @Column(name = "day22")
    private double day22;

    @Column(name = "day23")
    private double day23;

    @Column(name = "day24")
    private double day24;

    @Column(name = "day25")
    private double day25;

    @Column(name = "day26")
    private double day26;

    @Column(name = "day27")
    private double day27;

    @Column(name = "day28")
    private double day28;

    @Column(name = "day29")
    private double day29;

    @Column(name = "day30")
    private double day30;

    @Column(name = "day31")
    private double day31;

    @Column(name = "total")
    private double total;

    @Column(name = "average")
    private double average;

    @Column(name = "min")
    private double min;

    @Column(name = "max")
    private double max;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getDay1() {
        return day1;
    }

    public void setDay1(double day1) {
        this.day1 = day1;
    }

    public double getDay2() {
        return day2;
    }

    public void setDay2(double day2) {
        this.day2 = day2;
    }

    public double getDay3() {
        return day3;
    }

    public void setDay3(double day3) {
        this.day3 = day3;
    }

    public double getDay4() {
        return day4;
    }

    public void setDay4(double day4) {
        this.day4 = day4;
    }

    public double getDay5() {
        return day5;
    }

    public void setDay5(double day5) {
        this.day5 = day5;
    }

    public double getDay6() {
        return day6;
    }

    public void setDay6(double day6) {
        this.day6 = day6;
    }

    public double getDay7() {
        return day7;
    }

    public void setDay7(double day7) {
        this.day7 = day7;
    }

    public double getDay8() {
        return day8;
    }

    public void setDay8(double day8) {
        this.day8 = day8;
    }

    public double getDay9() {
        return day9;
    }

    public void setDay9(double day9) {
        this.day9 = day9;
    }

    public double getDay10() {
        return day10;
    }

    public void setDay10(double day10) {
        this.day10 = day10;
    }

    public double getDay11() {
        return day11;
    }

    public void setDay11(double day11) {
        this.day11 = day11;
    }

    public double getDay12() {
        return day12;
    }

    public void setDay12(double day12) {
        this.day12 = day12;
    }

    public double getDay13() {
        return day13;
    }

    public void setDay13(double day13) {
        this.day13 = day13;
    }

    public double getDay14() {
        return day14;
    }

    public void setDay14(double day14) {
        this.day14 = day14;
    }

    public double getDay15() {
        return day15;
    }

    public void setDay15(double day15) {
        this.day15 = day15;
    }

    public double getDay16() {
        return day16;
    }

    public void setDay16(double day16) {
        this.day16 = day16;
    }

    public double getDay17() {
        return day17;
    }

    public void setDay17(double day17) {
        this.day17 = day17;
    }

    public double getDay18() {
        return day18;
    }

    public void setDay18(double day18) {
        this.day18 = day18;
    }

    public double getDay19() {
        return day19;
    }

    public void setDay19(double day19) {
        this.day19 = day19;
    }

    public double getDay20() {
        return day20;
    }

    public void setDay20(double day20) {
        this.day20 = day20;
    }

    public double getDay21() {
        return day21;
    }

    public void setDay21(double day21) {
        this.day21 = day21;
    }

    public double getDay22() {
        return day22;
    }

    public void setDay22(double day22) {
        this.day22 = day22;
    }

    public double getDay23() {
        return day23;
    }

    public void setDay23(double day23) {
        this.day23 = day23;
    }

    public double getDay24() {
        return day24;
    }

    public void setDay24(double day24) {
        this.day24 = day24;
    }

    public double getDay25() {
        return day25;
    }

    public void setDay25(double day25) {
        this.day25 = day25;
    }

    public double getDay26() {
        return day26;
    }

    public void setDay26(double day26) {
        this.day26 = day26;
    }

    public double getDay27() {
        return day27;
    }

    public void setDay27(double day27) {
        this.day27 = day27;
    }

    public double getDay28() {
        return day28;
    }

    public void setDay28(double day28) {
        this.day28 = day28;
    }

    public double getDay29() {
        return day29;
    }

    public void setDay29(double day29) {
        this.day29 = day29;
    }

    public double getDay30() {
        return day30;
    }

    public void setDay30(double day30) {
        this.day30 = day30;
    }

    public double getDay31() {
        return day31;
    }

    public void setDay31(double day31) {
        this.day31 = day31;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }


}
