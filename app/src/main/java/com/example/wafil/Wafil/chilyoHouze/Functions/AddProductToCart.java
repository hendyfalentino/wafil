package com.example.wafil.Wafil.chilyoHouze.Functions;

import android.content.Context;
import android.util.Log;

import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductToCart {

    private cartHandler handler;
    private CustomProgressBar customProgressBar;

    public AddProductToCart(cartHandler handler) {
        this.handler = handler;
    }

    public void addToCart(Context context, String id_user, String product_id, int product_qty, int product_price, String note, String deliver_to_lat, String deliver_to_long, String deliver_to_string_place, String deliver_to_date){
        customProgressBar = new CustomProgressBar();

        if(deliver_to_date.equals("")){
            handler.onFailure("Tanggal belum ditentukan!");
            return;
        }

        if(deliver_to_string_place.equals("")){
            handler.onFailure("Lokasi belum ditentukan!");
            return;
        }

        customProgressBar.show(context, "Mohon tunggu");
        ApiInterface cart = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ShoppingCart> call = cart.addToCart(id_user, product_id, product_qty, product_price, note, deliver_to_lat, deliver_to_long, deliver_to_string_place, deliver_to_date);
        call.enqueue(new Callback<ShoppingCart>() {
            @Override
            public void onResponse(Call<ShoppingCart> call, Response<ShoppingCart> response) {
                Log.d("getData", String.valueOf(response.isSuccessful()));
                Log.d("getData", String.valueOf(response.message()));
                if(response.isSuccessful()){
                    handler.onSuccess();
                    customProgressBar.getDialog().dismiss();
                }
            }
            @Override
            public void onFailure(Call<ShoppingCart> call, Throwable t) {
                handler.onFailure("Error menambahkan data");
                customProgressBar.getDialog().dismiss();
                Log.d("getData", t.toString());
            }
        });
    }

    public interface cartHandler{
        void onSuccess();
        void onFailure(String kode);
    }
}
