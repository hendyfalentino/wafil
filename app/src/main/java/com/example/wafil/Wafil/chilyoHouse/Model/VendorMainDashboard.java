package com.example.wafil.Wafil.chilyoHouse.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VendorMainDashboard {
    @SerializedName("vendor_id")
    @Expose
    private String vendor_id ;

    @SerializedName("vendor_name")
    @Expose
    private String vendor_name ;

    @SerializedName("vendor_desc")
    @Expose
    private String vendor_desc;

    @SerializedName("vendor_img")
    @Expose
    private String vendor_img ;

    @SerializedName("vendor_rating")
    @Expose
    private String vendor_rating ;

    @SerializedName("vendor_product")
    @Expose
    private List<VendorProduct> vendor_product;

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

    public String getVendor_desc() {
        return vendor_desc;
    }

    public void setVendor_desc(String vendor_desc) {
        this.vendor_desc = vendor_desc;
    }

    public String getVendor_img() {
        return vendor_img;
    }

    public void setVendor_img(String vendor_img) {
        this.vendor_img = vendor_img;
    }

    public String getVendor_rating() {
        return vendor_rating;
    }

    public void setVendor_rating(String vendor_rating) {
        this.vendor_rating = vendor_rating;
    }

    public List<VendorProduct> getVendor_product() {
        return vendor_product;
    }

    public void setVendor_product(List<VendorProduct> vendor_product) {
        this.vendor_product = vendor_product;
    }
}

