package com.example.wafil.Wafil.tukangKu.rest.request;

import com.google.gson.annotations.SerializedName;

public class GetTukangRequest {
    @SerializedName("worktype")
    private int worktype;

    public GetTukangRequest setWorktype(int worktype) {
        this.worktype = worktype;
        return this;
    }
}
