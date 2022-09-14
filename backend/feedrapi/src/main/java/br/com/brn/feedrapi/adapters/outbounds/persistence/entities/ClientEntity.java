package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "gl00clie", schema = "global")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co00clie", nullable = false)
    private Integer id;

    @Column(name = "fantclie", nullable = false)
    private String fantasyName;

    @Column(name = "baseclie", nullable = false, length = 6)
    private String base;

    @Column(name = "domiclie", nullable = false, length = 250)
    private String domain;

    @Column(name = "statclie", nullable = false)
    private Integer status;

    @Column(name = "dtreclie")
    private Instant registerAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "co00clie")
    private ClientParamEntity param;

}
