package com.example.wafil.Wafil.chilyoHouze.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentError {

    @SerializedName("user_id")
    @Expose
    private String user_id;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
