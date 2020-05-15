package com.example.wafil.Wafil.tukangKu.rest.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }
}
