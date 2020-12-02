package com.am.libraries.logger.model.data;

import java.io.Serializable;

public class AuthorizedUser implements Serializable {
    private String id;
    private String username;
    private String token;

    public AuthorizedUser() {
    }

    public AuthorizedUser(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
                ", token='" + (token != null ? "Token" : "Null") + "\'\n" +
                '}';
    }
}
