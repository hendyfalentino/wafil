package com.example.wafil.Wafil.chilyoHouze.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VendorProduct {

    @SerializedName("shopcart_id")
    @Expose
    private String shopcart_id;

    @SerializedName("product_id")
    @Expose
    private String product_id;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("product_price")
    @Expose
    private String product_price;

    @SerializedName("product_qty")
    @Expose
    private String product_qty;

    @SerializedName("product_desc")
    @Expose
    private String product_desc;

    @SerializedName("product_note")
    @Expose
    private String product_note;

    public String getShopcart_id() {
        return shopcart_id;
    }

    public void setShopcart_id(String shopcart_id) {
        this.shopcart_id = shopcart_id;
    }

    public String getProduct_note() {
        return product_note;
    }

    public void setProduct_note(String product_note) {
        this.product_note = product_note;
    }

    public String getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(String product_qty) {
        this.product_qty = product_qty;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}

