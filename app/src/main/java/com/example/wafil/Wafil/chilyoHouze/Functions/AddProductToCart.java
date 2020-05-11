package com.example.wafil.Wafil.chilyoHouze.Functions;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct.ProductDetail;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
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

    public void addToCart(Context context, String id_user, String product_id, int product_qty, int product_price, String note){
        customProgressBar = new CustomProgressBar();
        customProgressBar.show(context, "Mohon tunggu");
        ApiInterface cart = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ShoppingCart> call = cart.addToCart(id_user, product_id, product_qty, product_price, note);
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
                handler.onFailure();
                customProgressBar.getDialog().dismiss();
                Log.d("getData", t.toString());
            }
        });
    }

    public interface cartHandler{
        void onSuccess();
        void onFailure();
    }
}
