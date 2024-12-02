package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CapitalisationOfProfitRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private double totalProfit;
    private double differenceInEquity;
    private double depreciation;
    private double assetOutflow;
    private double assetInflow;
    private double capitalisationGap;
    private double percentageProfitAccumulated;
    private String commentOnCapitalisation;    public String getLoanId() {
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
