package com.example.wafil.Wafil.chilyoHouze.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorMainOnline {
    @SerializedName("id_user")
    @Expose
    private String id_user;

    @SerializedName("name_user")
    @Expose
    private String name_user;

    @SerializedName("pic_user")
    @Expose
    private String pic_user;

    @SerializedName("phone_num_user")
    @Expose
    private String phone_num_user;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
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

