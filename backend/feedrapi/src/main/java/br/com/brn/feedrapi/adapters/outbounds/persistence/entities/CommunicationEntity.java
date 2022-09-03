package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "CL00COMU")
public class CommunicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO00COMU", nullable = false)
    private Long id;
}
