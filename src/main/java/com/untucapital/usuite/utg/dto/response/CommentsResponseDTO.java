package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CommentsResponseDTO extends AbstractEntityDTO {
    private String loanId;
    private String comment1;
    private String comment2;
    private String comment3;
    private String comment4;
    private String comment5;
    private String comment6;
    private String comment7;
    private String comment8;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public String getComment6() {
        return comment6;
    }

    public void setComment6(String comment6) {
        this.comment6 = comment6;
    }

    public String getComment7() {
        return comment7;
    }

    public void setComment7(String comment7) {
        this.comment7 = comment7;
    }

    public String getComment8() {
        return comment8;
    }

    public void setComment8(String comment8) {
        this.comment8 = comment8;
    }
}
