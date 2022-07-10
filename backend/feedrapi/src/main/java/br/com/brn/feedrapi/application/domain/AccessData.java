package br.com.brn.feedrapi.application.domain;


import java.util.ArrayList;


public class AccessData {

    User loggedUser;

    public AccessData() {
    }

    public AccessData(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

}
