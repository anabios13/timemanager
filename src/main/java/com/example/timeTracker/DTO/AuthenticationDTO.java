package com.example.timeTracker.DTO;

public class AuthenticationDTO {
    private String login;
    private String password;

    public AuthenticationDTO() {}

    public String getLogin() {
        return login;
    }

    public AuthenticationDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
