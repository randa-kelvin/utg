package com.untucapital.usuite.utg.dto.response;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.cms.CmsUser;
import com.untucapital.usuite.utg.model.po.PoUser;

import java.util.HashSet;
import java.util.Set;

public class UserResponseDTO extends AbstractEntityDTO {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String resetPasswordToken;

    private ContactDetailResponseDTO contactDetail;

    private CmsUser cmsUser;

    private PoUser poUser;

    private boolean active;

    private boolean verified;

    private String branch;

    private String creditCommitGroup;

    private String dirtOfBirth;

    private String maritalStatus;

    private String Gender;

    private String City;

    private String suburb;

    private String streetName;

    private String streetNumber;

    private String musoniClientId;

    private Set<RoleResponseDTO> roles = new HashSet<>();

    public String getCreditCommitGroup() {
        return creditCommitGroup;
    }

    public void setCreditCommitGroup(String creditCommitGroup) {
        this.creditCommitGroup = creditCommitGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public ContactDetailResponseDTO getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(ContactDetailResponseDTO contactDetail) {
        this.contactDetail = contactDetail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Set<RoleResponseDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleResponseDTO> roles) {
        this.roles = roles;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDirtOfBirth() {
        return dirtOfBirth;
    }

    public void setDirtOfBirth(String dirtOfBirth) {
        this.dirtOfBirth = dirtOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getMusoniClientId() {
        return musoniClientId;
    }

    public void setMusoniClientId(String musoniClientId) {
        this.musoniClientId = musoniClientId;
    }

    public CmsUser getCmsUser() {
        return cmsUser;
    }

    public void setCmsUser(CmsUser cmsUser) {
        this.cmsUser = cmsUser;
    }

    public PoUser getPoUser() {
        return poUser;
    }

    public void setPoUser(PoUser poUser) {
        this.poUser = poUser;
    }
}
