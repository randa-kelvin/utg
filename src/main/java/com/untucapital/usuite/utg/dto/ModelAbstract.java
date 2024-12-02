package com.untucapital.usuite.utg.dto;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Embeddable
public class ModelAbstract implements Serializable {
    private Integer id;
    private String code;
    private String value;
}
