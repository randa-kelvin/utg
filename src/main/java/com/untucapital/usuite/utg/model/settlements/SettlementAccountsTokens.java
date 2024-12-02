package com.untucapital.usuite.utg.model.settlements;

import com.untucapital.usuite.utg.model.AbstractEntity;
import com.untucapital.usuite.utg.model.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "settlements_confirmation_tokens")
public class SettlementAccountsTokens extends AbstractEntity {

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Column(name = "date_confirmed")
    private LocalDateTime dateConfirmed;

    @Column(name = "client_id")
    private String clientId;

}
