package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "GL00MAIL", schema = "GLOBAL")
public class EmailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "CO00MAIL")
    private Long id;

    @Column(name = "REFEMAIL")
    private String ownerRef;

    @Column(name = "PARAMAIL", nullable = false)
    private String to;

    @Column(name = "ASUNMAIL", nullable = false)
    private String subject;

    @Column(name = "CONTMAIL")
    private String content;

    @Column(name = "DTENMAIL")
    private Instant sendDate;

    @Column(name = "DTCRMAIL")
    private Instant creatAt;

    @Type(type="numeric_boolean")
    @Column(name = "STATMAIL")
    private Boolean status;

}
