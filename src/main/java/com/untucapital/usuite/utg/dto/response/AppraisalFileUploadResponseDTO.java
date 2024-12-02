package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppraisalFileUploadResponseDTO extends AbstractEntityDTO {


    private String fileName;

    private String fileType;

    private String fileDescription;

    private String userId;

    private String loanId;

}
