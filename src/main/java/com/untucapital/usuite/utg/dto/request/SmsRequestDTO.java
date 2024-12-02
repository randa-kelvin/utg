package com.untucapital.usuite.utg.dto.request;


import com.untucapital.usuite.utg.dto.AbstractEntityDTO;


public class SmsRequestDTO extends AbstractEntityDTO {


    private String originator;

    private String destination;

    private String messageText;

    private String messageReference;

    private String messageDate;

    private String messageValidity;

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
