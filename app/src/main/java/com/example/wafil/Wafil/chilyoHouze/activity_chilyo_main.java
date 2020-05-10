package com.example.wafil.Wafil.chilyoHouze;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Adapters.MainServiceVendorOnlineAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorMainOnline;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_chilyo_main extends AppCompatActivity {

    RecyclerView recyclerView;
    MainServiceVendorOnlineAdapter adapter;
    List<VendorMainOnline> vendorMainOnlineList;
    Intent intentSettings;
    Context context;
    CustomProgressBar progress;
    SessionManager sessionManager;
    String getUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_main);

        recyclerView = findViewById(R.id.main_service_vendor_view);

        elementInit();
        getJson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getJson();
    }

    private void elementInit(){

        // inisialisasi progress bar
        progress = new CustomProgressBar();

        ImageView activity_chilyo_back = findViewById(R.id.backButton);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // pergi ke halaman shopping cart
        ImageView shopping_cart_button = findViewById(R.id.activity_chilyo_cart);
        shopping_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, ActivityShoppingCart.class);
                startActivity(intentSettings);
            }
        });

        // go to history
        ImageView history_button = findViewById(R.id.activity_chilyo_history);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_history.class);
                startActivity(intentSettings);
            }
        });

        // go to Make Up
        LinearLayout acitvity_chilyo_make_up_button = findViewById(R.id.activity_chilyo_makeUp);
        acitvity_chilyo_make_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_makeup.class);
                startActivities(intentSettings);
            }
        });

        // go to Self Nail
        ImageView acitvity_chilyo_self_nail_button = findViewById(R.id.acitvity_chilyo_self_nail_button);
        acitvity_chilyo_self_nail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_selfnail.class);
                startActivities(intentSettings);
            }
        });

        // go to Spa
        ImageView acitvity_chilyo_spa_button = findViewById(R.id.acitvity_chilyo_spa_button);
        acitvity_chilyo_spa_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_spa.class);
                startActivities(intentSettings);
            }
        });

        // go to HairCare
        ImageView acitvity_chilyo_hair_care_button = findViewById(R.id.acitvity_chilyo_hair_care_button);
        acitvity_chilyo_hair_care_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_haircare.class);
                startActivities(intentSettings);
            }
        });
    }

    private void startActivities(Intent intentSettings) {
    }
    /*Method to generate List of data using RecyclerView with custom adapter*/

    private void getJson(){
        //progress.show(this, "Loading");
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<VendorMainOnline>> call = service.getOnlineVendors("180");
        call.enqueue(new Callback<List<VendorMainOnline>>() {
            @Override
            public void onResponse(Call<List<VendorMainOnline>> call, Response<List<VendorMainOnline>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<VendorMainOnline>> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<VendorMainOnline> vendorMainOnline) {
        adapter = new MainServiceVendorOnlineAdapter(this, this, vendorMainOnline);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity_chilyo_main.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
