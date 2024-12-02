package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class SwotRiskAnalysisResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String strength;

    private String weaknesses;

    private String opportunities;

    private String threats;

    public SwotRiskAnalysisResponseDTO() {
    }

    public SwotRiskAnalysisResponseDTO(String loanId, String strength, String weaknesses, String opportunities, String threats) {
        this.loanId = loanId;
        this.strength = strength;
        this.weaknesses = weaknesses;
        this.opportunities = opportunities;
        this.threats = threats;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(String opportunities) {
        this.opportunities = opportunities;
    }

    public String getThreats() {
        return threats;
    }

    public void setThreats(String threats) {
        this.threats = threats;
    }

    @Override
    public String toString() {
        return "SwotRiskAnalysisController{" +
                "loanId='" + loanId + '\'' +
                ", strength='" + strength + '\'' +
                ", weaknesses='" + weaknesses + '\'' +
                ", opportunities='" + opportunities + '\'' +
                ", threats='" + threats + '\'' +
                '}';
    }
}
