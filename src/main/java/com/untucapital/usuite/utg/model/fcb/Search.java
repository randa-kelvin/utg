package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_searches")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search extends AbstractEntity {

    @JsonProperty(value = "search_purpose")
    private String searchPurpose;

    @JsonProperty(value = "date_searched")
    private String dateSearched;

    private String status;

    private Integer score;

    @JsonProperty(value = "subscriber_name")
    private String subscriberName;

    @JsonProperty(value = "branch_name")
    private String branchName;

    @JsonProperty(value = "subscriber_short_name")
    private String subscriberShortName;

    public String getSearchPurpose() {
        return searchPurpose;
    }

    public void setSearchPurpose(String searchPurpose) {
        this.searchPurpose = searchPurpose;
    }

    public String getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(String dateSearched) {
        this.dateSearched = dateSearched;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSubscriberShortName() {
        return subscriberShortName;
    }

    public void setSubscriberShortName(String subscriberShortName) {
        this.subscriberShortName = subscriberShortName;
    }
}
