package com.example.wafil.Wafil.wafil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Adapters.MainServiceVendorOnlineAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorMainOnline;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_main;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_makeup;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_selfnail;

import java.util.List;

public class ServiceTypeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_service_type);

        recyclerView = findViewById(R.id.main_service_vendor_view);
        elementInit();
    }

    private void elementInit() {

        ImageView back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView delivery = findViewById(R.id.acitvity_chilyo_make_up_button1);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceTypeActivity.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });

        ImageView pick_up = findViewById(R.id.acitvity_chilyo_self_nail_button1);
        pick_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceTypeActivity.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });

}

    private void startActivities(Intent intentSettings) {
    }

    private void generateDataList(List<VendorMainOnline> vendorMainOnline) {
        adapter = new MainServiceVendorOnlineAdapter(this, this, vendorMainOnline);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ServiceTypeActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
