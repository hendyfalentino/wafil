package com.example.wafil.Wafil.atorJo;

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
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_history;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_main;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    MainServiceVendorOnlineAdapter adapter;
    List<VendorMainOnline> vendorMainOnlineList;
    Intent intentSetting;
    ImageView activity_history, activity_cart;
    Context context;
    CustomProgressBar progress;
    SessionManager sessionManager;
    String getUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        recyclerView = findViewById(R.id.main_service_vendor_view);

        elementInit();
        getJson();

        //getSupportActionBar().setTitle("atorJo");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void elementInit() {

        ImageView back = findViewById(R.id.activity_atorjo_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView activity_history = findViewById(R.id.activity_history);
        activity_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSetting =  new Intent(Activity2.this, activity_chilyo_history.class);
                startActivities(intentSetting);
            }
        });

        ImageView activity_cart = findViewById(R.id.activity_cart);
        activity_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSetting = new Intent(Activity2.this, ActivityShoppingCart.class);
                startActivities(intentSetting);
            }

        });

        ImageView rumah = findViewById(R.id.activity_atorjo_bersih_rmh_button);
        rumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, bersihRumah.class);
                startActivities(intent);
            }
        });

        ImageView kantor = findViewById(R.id.activity_atorjo_bersih_kantor_button);
        kantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, bersihKantor.class);
                startActivities(intent);
            }
        });
    }

    private void startActivities(Intent intentSetting) {
    }

    private void getJson(){
        //progress.show(this, "Loading");
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<VendorMainOnline>> call = service.getOnlineVendors("120");
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Activity2.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
