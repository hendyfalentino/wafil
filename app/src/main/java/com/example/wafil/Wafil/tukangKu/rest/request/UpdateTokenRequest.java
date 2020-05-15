package com.example.wafil.Wafil.tukangKu.rest.request;

import com.google.gson.annotations.SerializedName;

public class UpdateTokenRequest {
    @SerializedName("userId")
    private int userId;

    @SerializedName("token")
    private String token;

    public UpdateTokenRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UpdateTokenRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
