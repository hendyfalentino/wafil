package com.example.wafil.Wafil.chilyoHouze.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingCartGroup {
    @SerializedName("vendor_id")
    @Expose
    private String vendor_id;

    @SerializedName("vendor_name")
    @Expose
    private String vendor_name;

    @SerializedName("vendor_products")
    @Expose
    private List<VendorProduct> vendor_products;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public List<VendorProduct> getVendor_products() {
        return vendor_products;
    }

    public void setVendor_products(List<VendorProduct> vendor_products) {
        this.vendor_products = vendor_products;
    }
}

