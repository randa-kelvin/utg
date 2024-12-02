package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_addresses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address extends AbstractEntity {

    @Column(name = "street_no")
    @JsonProperty(value = "street_no")
    private String streetNo;

    @Column(name = "street_name")
    @JsonProperty(value = "street_name")
    private String streetName;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "building")
    private String building;

    @Column(name = "city")
    private String city;

    @Column(name = "date_searched")
    @JsonProperty(value = "date_searched")
    private String dateSearched;

    @Column(name = "country")
    private String country;

    @Column(name = "property_status")
    @JsonProperty(value = "property_status")
    private String propertyStatus;

    @Column(name = "property_density")
    @JsonProperty(value = "property_density")
    private String propertyDensity;


    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(String dateSearched) {
        this.dateSearched = dateSearched;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getPropertyDensity() {
        return propertyDensity;
    }

    public void setPropertyDensity(String propertyDensity) {
        this.propertyDensity = propertyDensity;
    }
}
