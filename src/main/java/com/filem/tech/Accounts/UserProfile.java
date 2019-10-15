package com.filem.tech.Accounts;

public class UserProfile {
    private String login;
    private String passsword;
    private String email;

    public UserProfile(String login, String passsword, String email) {
        this.login = login;
        this.passsword = passsword;
        this.email = email;
    }

    public String getLogin() {return login;}
    public String getPasssword() {return passsword;}
    public String getEmail() {return email;}
}
