package com.example.wafil.Wafil.tukangKu.rest.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }
}
