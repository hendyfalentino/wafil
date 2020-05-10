package com.example.wafil.Wafil.wafil;

import com.google.gson.annotations.SerializedName;

public class Category_Product {
    @SerializedName("name_category_product") private String name_category_product;
    @SerializedName("pricen") private String pricen;
    @SerializedName("pricex") private String pricex;
    @SerializedName("nprice") private String nprice;
    @SerializedName("xprice") private String xprice;

    public String getName_category_product() {
        return name_category_product;
    }

    public String getPricen() {
        return pricen;
    }

    public String getPricex() {
        return pricex;
    }

    public String getNprice() {
        return nprice;
    }

    public String getXprice() {
        return xprice;
    }
}
