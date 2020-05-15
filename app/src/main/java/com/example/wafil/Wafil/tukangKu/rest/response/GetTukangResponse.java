package com.example.wafil.Wafil.tukangKu.rest.response;

import com.example.wafil.Wafil.tukangKu.component.TukangItem;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;

public class GetTukangResponse extends BaseResponse{
    @SerializedName("data")
    private List<TukangItem> data;

    public Collection<? extends TukangItem> getData() {
        return data;
    }
}
