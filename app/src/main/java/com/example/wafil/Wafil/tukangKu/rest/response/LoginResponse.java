package com.example.wafil.Wafil.tukangKu.rest.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseResponse {
    @SerializedName("user_first_name")
    private String name;

    @SerializedName("user_name")
    private String username;

    @SerializedName("user_id")
    private int id;

    @SerializedName("type")
    private int type;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }
}
