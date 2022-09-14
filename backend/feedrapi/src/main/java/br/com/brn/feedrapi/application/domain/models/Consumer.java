package br.com.brn.feedrapi.application.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Consumer {

    private Long id;

    private String email;

    private String name;

    private LocalDate birthDate;

    private String gender;

    private String photo;

    private Boolean active;

    @JsonIgnore
    String password;

    public boolean isNew() {
        return this.id <= 0;
    }
}
