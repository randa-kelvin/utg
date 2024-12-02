package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppraisalFileUploadRequestDTO extends AbstractEntityDTO {


    private String fileName;

    private String fileType;

    private String fileDescription;

    private String userId;

    private String loanId;

}
