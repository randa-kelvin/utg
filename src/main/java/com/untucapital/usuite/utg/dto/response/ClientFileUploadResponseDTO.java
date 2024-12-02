package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.model.AbstractEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ClientFileUploadResponseDTO extends AbstractEntity {


    private String fileName;

    private String fileDescription;

    private String userId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
