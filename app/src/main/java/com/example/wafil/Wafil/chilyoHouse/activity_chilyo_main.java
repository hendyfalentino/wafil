package com.example.wafil.Wafil.chilyoHouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouse.Adapters.MainServiceVendorOnlineAdapter;
import com.example.wafil.Wafil.chilyoHouse.Model.VendorMainOnline;
import com.example.wafil.Wafil.chilyoHouse.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouse.Support.CustomProgressBar;

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
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.main_service_vendor_view);

        /** Simulasi login - Hapus ini hen klo smo pake nni punya **/
        HashMap<String, String> user = sessionManager.getUserDetail();
        getUserId = user.get(SessionManager.user_id);
        //SessionManager.setAuthUserId("2020001", getBaseContext());
        //String data = SessionManager.getAuthUserId(getBaseContext());
        String data = getUserId;
        Log.d("GetData", data);
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

        // pergi ke halaman shopping cart
        ImageView shopping_cart_button = findViewById(R.id.activity_chilyo_cart);
        shopping_cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, ActivityShoppingCart.class);
                startActivity(intentSettings);
            }
        });

        // go to rating
        ImageView rating_button = findViewById(R.id.activity_chilyo_history);
        rating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(activity_chilyo_main.this, activity_chilyo_rating.class);
                startActivity(intentSettings);
            }
        });
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
                //progress.getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<List<VendorMainOnline>> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<VendorMainOnline> vendorMainOnline) {
        adapter = new MainServiceVendorOnlineAdapter(this, vendorMainOnline);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity_chilyo_main.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
