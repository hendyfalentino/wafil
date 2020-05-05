package com.example.wafil.Wafil.chilyoHouze.Model;

public class ShoppingCart {
    private String id_user;
    private String id_service_product ;
    private int shopcart_qty ;
    private int shopcart_price;
    private String shopcart_note;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_service_product() {
        return id_service_product;
    }

    public void setId_service_product(String id_service_product) {
        this.id_service_product = id_service_product;
    }

    public int getShopcart_qty() {
        return shopcart_qty;
    }

    public void setShopcart_qty(int shopcart_qty) {
        this.shopcart_qty = shopcart_qty;
    }

    public int getShopcart_price() {
        return shopcart_price;
    }

    public void setShopcart_price(int shopcart_price) {
        this.shopcart_price = shopcart_price;
    }

    public String getShopcart_note() {
        return shopcart_note;
    }

    public void setShopcart_note(String shopcart_note) {
        this.shopcart_note = shopcart_note;
    }
}

