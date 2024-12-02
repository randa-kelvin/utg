package com.untucapital.usuite.utg.pos.dto;

import com.untucapital.usuite.utg.model.enums.cms.ApprovalStatus;
import com.untucapital.usuite.utg.pos.model.POSSuplierFileUploads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POSParameterDto extends AbstractEntityDTO {
    private Double cumulative;
    private Double tax;
}
