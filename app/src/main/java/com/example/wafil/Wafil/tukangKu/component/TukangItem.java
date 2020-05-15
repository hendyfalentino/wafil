package com.example.wafil.Wafil.tukangKu.component;

import com.google.gson.annotations.SerializedName;

public class TukangItem {
    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("user_id")
    private int id;

    @SerializedName("worktype")
    private int worktype;

    public int getId() {
        return id;
    }

    public int getWorktype() {
        return worktype;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public TukangItem setName(String name) {
        this.name = name;
        return this;
    }

    public TukangItem setAddress(String address) {
        this.address = address;
        return this;
    }
}
