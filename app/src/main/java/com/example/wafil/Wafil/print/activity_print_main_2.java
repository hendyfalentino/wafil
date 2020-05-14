package com.example.wafil.Wafil.print;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Adapters.MainServiceVendorOnlineAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorMainOnline;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;
import com.example.wafil.Wafil.wafil.CategoryProductActivity;

import java.util.List;

public class activity_print_main_2 extends AppCompatActivity {


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
        setContentView(R.layout.activity_print_main);

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

        ImageView delivery = findViewById(R.id.acitvity_print_button);
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_print_main_2.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });

        ImageView pick_up = findViewById(R.id.acitvity_scan_button);
        pick_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_print_main_2.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });

    }

    private void startActivities(Intent intentSettings) {
    }

    private void generateDataList(List<VendorMainOnline> vendorMainOnline) {
        adapter = new MainServiceVendorOnlineAdapter(this, this, vendorMainOnline);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity_print_main_2.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
