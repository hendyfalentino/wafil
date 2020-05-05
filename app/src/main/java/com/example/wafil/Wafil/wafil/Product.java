package com.example.wafil.Wafil.wafil;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name_product") private String name_product;
    @SerializedName("price") private String price;

    public String getName_product() {
        return name_product;
    }

    public String getPrice() {
        return price;
    }
}
