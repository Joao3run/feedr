package br.com.brn.feedrapi.application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class User {
    private long id;

    private String fullName;

    private String gender;

    private String email;

    private String username;

    private Date birthDate;

    private Boolean active;

    @JsonIgnore
    String password;

    @JsonIgnore
    Date creatAt;

    @JsonIgnore
    Date updateAt;
    String clientSchema;

    public User() {
    }

    public User(long id, String fullName, String gender, String email, String username, String password, Date creatAt, Date updateAt, String clientSchema) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.creatAt = creatAt;
        this.updateAt = updateAt;
        this.clientSchema = clientSchema;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientSchema() {
        return clientSchema;
    }

    public void setClientSchema(String clientSchema) {
        this.clientSchema = clientSchema;
    }

    public boolean isNew() {
        return this.id <= 0;
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


