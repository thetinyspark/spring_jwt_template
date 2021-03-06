package com.blizzard.hearstone.security;

import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String name;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String username, String password) {
        this.setName(username);
        this.setPassword(password);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}