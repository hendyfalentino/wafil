package com.example.wafil.Wafil.atorJo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_history;

public class Activity2 extends AppCompatActivity {

    Intent intentSetting;
    ImageView activity_history, activity_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        elementInit();

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
}
