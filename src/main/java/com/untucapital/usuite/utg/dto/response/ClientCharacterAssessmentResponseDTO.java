package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;

@Data
public class ClientCharacterAssessmentResponseDTO extends AbstractEntityDTO {

    private String loanId;

    private String description;

    public ClientCharacterAssessmentResponseDTO() {
    }

    public ClientCharacterAssessmentResponseDTO(String loanId, String description) {
        this.loanId = loanId;
        this.description = description;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClientCharacterAssessmentService{" +
                "loanId='" + loanId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
