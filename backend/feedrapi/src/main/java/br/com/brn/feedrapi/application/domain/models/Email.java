package br.com.brn.feedrapi.application.domain.models;

import lombok.Data;

import java.time.Instant;

@Data
public class Email {

    private Long id;

    private String ownerRef;

    private String to;

    private String subject;

    private String content;

    private Instant sendDate;

    private Instant creatAt;

    private Boolean status;

    public Email(Long id, String ownerRef, String to, String subject, String content, Instant sendDate, Instant creatAt, Boolean status) {
        this.id = id;
        this.ownerRef = ownerRef;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.sendDate = sendDate;
        this.creatAt = creatAt;
        this.status = status;
    }

    public Email() {
    }

    public Email(Long id, String ownerRef, String to, String subject, String content, Instant creatAt, Boolean status) {
        this.id = id;
        this.ownerRef = ownerRef;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.creatAt = creatAt;
        this.status = status;
    }
}
