package com.example.wafil.Wafil.chilyoHouze.Model;

public class ShoppingCart {

    private String id_user;
    private String id_service_product ;
    private int shopcart_qty ;
    private int shopcart_price;
    private String shopcart_note;
    private String deliver_to_lat;
    private String deliver_to_long;
    private String deliver_to_string_place;
    private String deliver_to_date;

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

    public String getDeliver_to_lat() {
        return deliver_to_lat;
    }

    public void setDeliver_to_lat(String deliver_to_lat) {
        this.deliver_to_lat = deliver_to_lat;
    }

    public String getDeliver_to_long() {
        return deliver_to_long;
    }

    public void setDeliver_to_long(String deliver_to_long) {
        this.deliver_to_long = deliver_to_long;
    }

    public String getDeliver_to_string_place() {
        return deliver_to_string_place;
    }

    public void setDeliver_to_string_place(String deliver_to_string_place) {
        this.deliver_to_string_place = deliver_to_string_place;
    }

    public String getDeliver_to_date() {
        return deliver_to_date;
    }

    public void setDeliver_to_date(String deliver_to_date) {
        this.deliver_to_date = deliver_to_date;
    }
}

