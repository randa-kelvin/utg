package com.untucapital.usuite.utg.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author tjchidanika
 * @created 11/9/2023
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
public class POSCategoryRequestDTO {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;

}
