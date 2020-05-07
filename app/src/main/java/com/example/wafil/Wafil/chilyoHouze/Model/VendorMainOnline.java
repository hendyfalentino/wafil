package com.example.wafil.Wafil.chilyoHouze.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorMainOnline {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("user_name")
    @Expose
    private String user_name;

    @SerializedName("pic_user")
    @Expose
    private String pic_user;

    @SerializedName("phone_num_user")
    @Expose
    private String phone_num_user;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPic_user() {
        return pic_user;
    }

    public void setPic_user(String pic_user) {
        this.pic_user = pic_user;
    }

    public String getPhone_num_user() {
        return phone_num_user;
    }

    public void setPhone_num_user(String phone_num_user) {
        this.phone_num_user = phone_num_user;
    }
}

