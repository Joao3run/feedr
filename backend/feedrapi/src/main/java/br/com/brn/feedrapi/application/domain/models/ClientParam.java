package br.com.brn.feedrapi.application.domain.models;

import lombok.Data;

@Data
public class ClientParam {

    private Integer id;

    private Client client;

    private String mailTemplateNewUser;

    private String mailTemplateResetPassword;

}
