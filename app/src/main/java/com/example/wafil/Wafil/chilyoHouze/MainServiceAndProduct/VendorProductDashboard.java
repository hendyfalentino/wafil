package com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.chilyoHouze.Adapters.MainServiceVendorProductsAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorMainDashboard;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorProduct;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendorProductDashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    MainServiceVendorProductsAdapter adapter;
    TextView vendor_name, vendor_desc;
    ImageView vendor_img, backButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_service_vendor_product_dashboard);

        Intent intent = getIntent();
        String vendor_id = intent.getStringExtra("vendor_id");
        vendor_name  = findViewById(R.id.main_service_product_dashboard_vendor_name);
        vendor_img   = findViewById(R.id.main_service_product_dashboard_image);
        backButton   = findViewById(R.id.backButton);
        vendor_desc  = findViewById(R.id.main_service_product_dashboard_vendor_desc);
        recyclerView = findViewById(R.id.main_service_vendor_group);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d("dataFlow", vendor_id);
        getJson(vendor_id);
    }

    private void getJson(String vendor_id){
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<VendorMainDashboard> call = service.getSpecificVendor(vendor_id);
        call.enqueue(new Callback<VendorMainDashboard>() {
            @Override
            public void onResponse(@NonNull Call<VendorMainDashboard> call, @NonNull Response<VendorMainDashboard> response) {
                if(response.body() != null){
                    Picasso.with(context).load(response.body().getVendor_img()).into(vendor_img);
                    vendor_name.setText(response.body().getVendor_name());
                    vendor_desc.setText(response.body().getVendor_desc());
                    generateDataList(response.body().getVendor_product());
                }
            }

            @Override
            public void onFailure(Call<VendorMainDashboard> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<VendorProduct> vendorProduct) {
        adapter = new MainServiceVendorProductsAdapter(VendorProductDashboard.this, vendorProduct);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(VendorProductDashboard.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
