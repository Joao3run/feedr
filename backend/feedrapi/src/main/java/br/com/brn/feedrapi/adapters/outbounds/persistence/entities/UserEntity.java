package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CL00USUA", schema = "cl0001")
@SequenceGenerator(name = "co00usua_seq", sequenceName = "cl00usua_co00usua_seq", allocationSize = 1)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "co00usua_seq")
    @Column(name = "CO00USUA")
    private long id;

    @Column(name = "NOMEUSUA")
    String fullName;

    @Column(name = "GENEUSUA")
    String gender;

    @Column(name = "MAILUSUA")
    String email;

    @Column(name = "LOGIUSUA")
    String username;

    @Column(name = "PASSUSUA")
    String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTCRUSUA", updatable = false)
    Date creatAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTATUSUA")
    Date updateAt;

    @Column(name = "DTNCUSUA")
    private Date birthDate;

    @Type(type = "numeric_boolean")
    @Column(name = "ATIVUSUA")
    private Boolean active;

    @Column(name = "BASEUSUA")
    String clientSchema;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getClientSchema() {
        return clientSchema;
    }

    public void setClientSchema(String clientSchema) {
        this.clientSchema = clientSchema;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
