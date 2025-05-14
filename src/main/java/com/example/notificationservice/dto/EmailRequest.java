package com.example.notificationservice.dto;

import lombok.Data;

//@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
