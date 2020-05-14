package com.example.wafil.Wafil.chefCook.dbclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarinUrl {

    @Expose
    @SerializedName("username") private String nama;
    @Expose
    @SerializedName("email") private String email;
    @Expose
    @SerializedName("password") private String pass;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
