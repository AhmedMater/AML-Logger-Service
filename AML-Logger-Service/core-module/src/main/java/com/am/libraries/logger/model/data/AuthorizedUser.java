package com.am.libraries.logger.model.data;

import java.io.Serializable;

public class AuthorizedUser implements Serializable {
    private Long id;
    private String username;
    private String nameEN;
    private String token;

    public AuthorizedUser() {
    }

    public AuthorizedUser(Long id, String username, String nameEN) {
        this.id = id;
        this.username = username;
        this.nameEN = nameEN;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameEN() {
        return nameEN;
    }
    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthorizedUser{" +
                "id=" + id + "\n" +
                ", username='" + username + "\'\n" +
                ", nameEN='" + nameEN + "\'\n" +
                ", token='" + (token != null ? "Token" : "Null") + "\'\n" +
                '}';
    }
}
