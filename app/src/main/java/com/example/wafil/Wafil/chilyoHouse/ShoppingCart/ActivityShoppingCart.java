package com.example.wafil.Wafil.chilyoHouse.ShoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouse.Adapters.ShoppingCartGroupAdapter;
import com.example.wafil.Wafil.chilyoHouse.Model.ShoppingCartGroup;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShoppingCart extends AppCompatActivity {

    Intent intentSettings;
    ImageView activity_chilyo_back;
    ShoppingCartGroupAdapter groupAdapter;

    RecyclerView rv_shopping_cart_group;
    String id_user;
    SessionManager sessionManager;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        getSupportActionBar().hide();

        elementInit();

        /** menngambil id user **/
        HashMap<String, String> user = sessionManager.getUserDetail();
        //id_user = SessionManager.getAuthUserId(getBaseContext());
        id_user = user.get(SessionManager.user_id);
        layoutManager = new LinearLayoutManager(ActivityShoppingCart.this);
        rv_shopping_cart_group = findViewById(R.id.shopping_cart_group);
        getJson(id_user);
    }

    private void elementInit(){

        /** kembali ke menu utama **/
        activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getJson(String id_user){
        ApiInterface groupShopping = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ShoppingCartGroup>> call = groupShopping.getAllShoppingCart(id_user);
        call.enqueue(new Callback<List<ShoppingCartGroup>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartGroup>> call, Response<List<ShoppingCartGroup>> response) {
                generateShoppingGroup(response.body());
            }

            @Override
            public void onFailure(Call<List<ShoppingCartGroup>> call, Throwable t) {

            }
        });
    }

    private void generateShoppingGroup(List<ShoppingCartGroup> shoppingCartGroups) {
        groupAdapter = new ShoppingCartGroupAdapter(this, shoppingCartGroups);
        rv_shopping_cart_group.setLayoutManager(layoutManager);
        rv_shopping_cart_group.setAdapter(groupAdapter);
    }

    public static void refreshData(){

    }


}
