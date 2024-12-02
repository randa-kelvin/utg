package com.untucapital.usuite.utg.controller.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

public class SignUpRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 40)
    private String lastName;

    @NotBlank
    private String password;


    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    private Long mobileNumber;


    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }
}
