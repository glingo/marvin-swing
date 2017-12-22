package com.marvin.bundle.swing.resources.view;

public class LoginForm {
    
    private String login;
    private String password;

    public LoginForm() {
    }

    public LoginForm(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" + "login=" + login + ", password=" + password + '}';
    }
}
