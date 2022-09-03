package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GL00CONS", schema = "GLOBAL")
public class ConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co00cons", nullable = false)
    private Long id;

    @Column(name = "mailcons", nullable = false)
    private String email;

    @Column(name = "nomecons", nullable = false)
    private String name;

    @Column(name = "dtnccons", nullable = false)
    private LocalDate birthDate;

    @Column(name = "genecons", nullable = false)
    private String gender;

    @Column(name = "co00file", length = 50)
    private String photo;

    @Type(type = "numeric_boolean")
    @Column(name = "ATIVCONS")
    private Boolean active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
