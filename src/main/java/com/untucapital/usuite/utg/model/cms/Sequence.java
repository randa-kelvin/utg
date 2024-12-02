package com.untucapital.usuite.utg.model.cms;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sequence_generator")
@Data
public class Sequence {
    @Id
    private String name;

    private long nextValue;
}
