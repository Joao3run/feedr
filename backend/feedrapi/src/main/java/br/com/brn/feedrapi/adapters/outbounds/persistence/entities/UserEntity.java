package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CL00USUA", schema = "cl0001")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "CO00USUA")
    private long id;

    @Column(name = "NOMEUSUA")
    String fullName;

    @Column(name = "FINAUSUA")
    String firstName;

    @Column(name = "LANAUSUA")
    String lastName;

    @Column(name = "GENEUSUA")
    String gender;

    @Column(name = "MAILUSUA")
    String email;

    @Column(name = "LOGIUSUA")
    String username;

    @Column(name = "PASSUSUA")
    String password;

    @Column(name = "DTCRUSUA")
    Date creatAt;

    @Column(name = "DTATUSUA")
    Date updateAt;

}
