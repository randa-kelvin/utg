package com.untucapital.usuite.utg.DTO.request;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommentsOnFixedAssetsRequestDTO extends AbstractEntityDTO {

    private String loanId;
    private String description;

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
}
