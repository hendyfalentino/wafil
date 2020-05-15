package com.example.wafil.Wafil.tukangKu.rest.request;

import com.google.gson.annotations.SerializedName;

public class OrderRequest {
    @SerializedName("userId")
    private int userId;

    @SerializedName("tukangId")
    private int tukangId;

    @SerializedName("workaddress")
    private String workAddress;

    @SerializedName("worktype")
    private int worktype;

    public OrderRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public OrderRequest setTukangId(int tukangId) {
        this.tukangId = tukangId;
        return this;
    }

    public OrderRequest setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    public OrderRequest setWorktype(int worktype) {
        this.worktype = worktype;
        return this;
    }
}
