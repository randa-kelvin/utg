package com.untucapital.usuite.utg.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Chirinda Nyasha Dell 23/11/2021
 */

@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken extends AbstractEntity {

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "date_confirmed")
    private LocalDateTime dateConfirmed;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
