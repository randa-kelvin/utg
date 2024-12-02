package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fcb_exposures")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exposure extends AbstractEntity {
}
