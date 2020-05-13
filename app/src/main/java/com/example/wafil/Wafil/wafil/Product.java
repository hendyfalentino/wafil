package com.example.wafil.Wafil.wafil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id_product")
    @Expose
    private String id_product;

    @SerializedName("name_product") private String name_product;
    @SerializedName("price") private String price;
    @SerializedName("pic_user") private String pic_user;

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPic_user() {
        return pic_user;
    }

    public void setPic_user(String pic_user) {
        this.pic_user = pic_user;
    }
}
