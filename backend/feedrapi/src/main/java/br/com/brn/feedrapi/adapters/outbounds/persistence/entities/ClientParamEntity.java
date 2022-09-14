package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gl01clie", schema = "global")
public class ClientParamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co01clie", nullable = false)
    private Integer id;

    @Column(name = "emnuclie")
    private String mailTemplateNewUser;

    @Column(name = "emruclie")
    private String mailTemplateResetPassword;

}
