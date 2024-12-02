package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "capitalisationOfProfit")
public class CapitalisationOfProfit extends  AbstractEntity{
    @NotNull
    @Column(nullable = false, name = "loan_id")
    private String loanId;

    @Column(name = "total_profit")
    private double totalProfit;

    @Column(name = "difference_in_equity")
    private double differenceInEquity;

    @Column(nullable = false)
    private double depreciation;

    @Column(name = "asset_outflow")
    private double assetOutflow;

    @Column(name = "asset_inflow")
    private double assetInflow;

    @Column(name = "capitalisation_gap")
    private double capitalisationGap;

    @Column(name = "percentage_profit_accumulated")
    private double percentageProfitAccumulated;

    @Column(name = "comment_on_capitalisation")
    private String commentOnCapitalisation;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getDifferenceInEquity() {
        return differenceInEquity;
    }

    public void setDifferenceInEquity(double differenceInEquity) {
        this.differenceInEquity = differenceInEquity;
    }

    public double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(double depreciation) {
        this.depreciation = depreciation;
    }

    public double getAssetOutflow() {
        return assetOutflow;
    }

    public void setAssetOutflow(double assetOutflow) {
        this.assetOutflow = assetOutflow;
    }

    public double getAssetInflow() {
        return assetInflow;
    }

    public void setAssetInflow(double assetInflow) {
        this.assetInflow = assetInflow;
    }

    public double getCapitalisationGap() {
        return capitalisationGap;
    }

    public void setCapitalisationGap(double capitalisationGap) {
        this.capitalisationGap = capitalisationGap;
    }

    public double getPercentageProfitAccumulated() {
        return percentageProfitAccumulated;
    }

    public void setPercentageProfitAccumulated(double percentageProfitAccumulated) {
        this.percentageProfitAccumulated = percentageProfitAccumulated;
    }

    public String getCommentOnCapitalisation() {
        return commentOnCapitalisation;
    }

    public void setCommentOnCapitalisation(String commentOnCapitalisation) {
        this.commentOnCapitalisation = commentOnCapitalisation;
    }
}
