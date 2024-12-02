package com.untucapital.usuite.utg.pos.dto;

import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.pos.model.POSSuplierFileUploads;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.untucapital.usuite.utg.pos.model.POSSupplier}
 */

@RequiredArgsConstructor
@Data
public class POSSupplierDto implements Serializable {
    Integer id;
    String name;
    String address;
    String phone;
    String contactPerson;
    String comment;
    String taxIdNo;
    String taxClearance;
    ApprovalStatus status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    List<POSSuplierFileUploads> supplierFiles;
}
