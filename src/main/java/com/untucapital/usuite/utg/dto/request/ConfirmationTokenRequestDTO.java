package com.untucapital.usuite.utg.dto.request;

import com.untucapital.usuite.utg.dto.AbstractEntityDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@RequiredArgsConstructor
@Data
public class ConfirmationTokenRequestDTO extends AbstractEntityDTO {

    private String token;
    private LocalDateTime expirationDate;
    private LocalDateTime dateConfirmed;
    private UserRequestDTO user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getDateConfirmed() {
        return dateConfirmed;
    }

    public void setDateConfirmed(LocalDateTime dateConfirmed) {
        this.dateConfirmed = dateConfirmed;
    }

    public UserRequestDTO getUser() {
        return user;
    }

    public void setUser(UserRequestDTO user) {
        this.user = user;
    }
}
