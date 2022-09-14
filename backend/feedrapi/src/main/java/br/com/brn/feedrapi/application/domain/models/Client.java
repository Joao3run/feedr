package br.com.brn.feedrapi.application.domain.models;

import lombok.Data;

import java.time.Instant;

@Data
public class Client {

    private Integer id;

    private String fantasyName;

    private String base;

    private String domain;

    private Integer status;

    private Instant registerAt;

    private ClientParam param;

}
