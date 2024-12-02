package com.untucapital.usuite.utg.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sms")
public class Sms extends AbstractEntity {

    @Column(name = "originator")
    private String originator;

    @Column(name = "destination")
    private String destination;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "message_reference")
    private String messageReference;

    @Column(name = "message_date")
    private String messageDate;

    @Column(name = "message_validity")
    private String messageValidity;

    @Column(name = "send_date_time")
    private String sendDateTime;

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageReference() {
        return messageReference;
    }

    public void setMessageReference(String messageReference) {
        this.messageReference = messageReference;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageValidity() {
        return messageValidity;
    }

    public void setMessageValidity(String messageValidity) {
        this.messageValidity = messageValidity;
    }

    public String getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(String sendDateTime) {
        this.sendDateTime = sendDateTime;
    }
}
