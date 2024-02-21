package com.example.springbootfront.dto;

public class AuthDto {
    private String name;
    private String password;

    public AuthDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AuthDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
