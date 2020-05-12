package com.example.wafil.Wafil.chilyoHouze.ShoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Adapters.ShoppingCartGroupAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.ErrorClass;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCartGroup;
import com.example.wafil.Wafil.chilyoHouze.Payment.ActivityPayment;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_main;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityShoppingCart extends AppCompatActivity {

    Intent intentSettings;
    ImageView activity_chilyo_back;
    ImageView shopping_item_remove;
    Button pembyaran_btn;
    ShoppingCartGroupAdapter groupAdapter;
    TextView product_name;
    TextView product_price;
    LinearLayout empty_cart;

    CustomProgressBar progress;
    RecyclerView rv_shopping_cart_group;
    RecyclerView rv_shopping_cart_item;
    String id_user;
    SessionManager sessionManager;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);

        elementInit();
        progress = new CustomProgressBar();
        empty_cart = findViewById(R.id.empty_cart);

        /** menngambil id user **/
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(SessionManager.user_id);
        layoutManager = new LinearLayoutManager(ActivityShoppingCart.this);
        rv_shopping_cart_group = findViewById(R.id.shopping_cart_group);
        showProgress();
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

        /** ke pembyaran **/
        pembyaran_btn = findViewById(R.id.pembayaran_btn);
        pembyaran_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityShoppingCart.this, ActivityPayment.class);
                startActivity(intentSettings);
            }
        });

    }

    private void getJson(String id_user){
        ApiInterface groupShopping = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ShoppingCartGroup>> call = groupShopping.getAllShoppingCart(id_user);
        call.enqueue(new Callback<List<ShoppingCartGroup>>() {
            @Override
            public void onResponse(Call<List<ShoppingCartGroup>> call, Response<List<ShoppingCartGroup>> response) {
                if(response.body() != null){
                    generateShoppingGroup(response.body());
                    dismissProgress();
                }
                else{
                    Log.d("getData", "Shopping cart kosong, sama kaya hati kamu ;)");
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingCartGroup>> call, Throwable t) {

            }
        });
    }

    private void deleteData(String id_shopcart){
        showProgress();
        ApiInterface deleteCart = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<ErrorClass> call = deleteCart.deleteShoppingCart(id_shopcart);
        call.enqueue(new Callback<ErrorClass>() {
            @Override
            public void onResponse(Call<ErrorClass> call, Response<ErrorClass> response) {

            }
            @Override
            public void onFailure(Call<ErrorClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Item berhasil dihapus dari cart",Toast.LENGTH_SHORT).show();
                getJson(id_user);
                dismissProgress();
            }
        });
    }

    private void generateShoppingGroup(List<ShoppingCartGroup> shoppingCartGroups) {
        groupAdapter = new ShoppingCartGroupAdapter(this, shoppingCartGroups);
        Log.d("getData", String.valueOf(groupAdapter.getItemCount()));

        /** Jika shopping cart ada isi */
        if(groupAdapter.getItemCount() > 0){
            empty_cart.setVisibility(View.GONE);
            pembyaran_btn.setVisibility(View.VISIBLE);
        }

        /** Jika shopping cart kosong */
        else{
            empty_cart.setVisibility(View.VISIBLE);
            pembyaran_btn.setVisibility(View.GONE);
        }

        groupAdapter.setClickGroupListener(new ShoppingCartGroupAdapter.deleteGroupClickListener() {
            @Override
            public void onDeleteClick(String id) {
                deleteData(id);
            }
        });
        rv_shopping_cart_group.setLayoutManager(layoutManager);
        rv_shopping_cart_group.setAdapter(groupAdapter);
    }

    private void showProgress(){
        progress.show(this, "Mohon tunggu");
    }
    private void dismissProgress(){
        progress.getDialog().dismiss();
    }

}
