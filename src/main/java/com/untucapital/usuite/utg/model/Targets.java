package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "targets")
public class Targets extends AbstractEntity {

    private String branch;

    private String target;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
