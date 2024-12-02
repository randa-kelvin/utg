package com.untucapital.usuite.utg.dto.loanObjects;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ModelAbstract implements Serializable {
    private Integer id;
    private String code;
    private String value;
}
