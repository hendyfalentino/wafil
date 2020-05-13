package com.example.wafil.Wafil.wafil;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("product_id") private String product_id;
    @SerializedName("name_product") private String name_product;
    @SerializedName("price") private String price;
    @SerializedName("pic_user") private String pic_user;

    public String getProduct_id() {
        return product_id;
    }

    public String getName_product() {
        return name_product;
    }

    public String getPrice() {
        return price;
    }

    public String getPic_user() {
        return pic_user;
    }
}
