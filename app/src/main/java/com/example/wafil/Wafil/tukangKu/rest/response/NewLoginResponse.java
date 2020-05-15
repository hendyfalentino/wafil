package com.example.wafil.Wafil.tukangKu.rest.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewLoginResponse extends BaseResponse{
    @SerializedName("login")
    List<LoginResponse> data;

    public List<LoginResponse> getData() {
        return data;
    }
}
