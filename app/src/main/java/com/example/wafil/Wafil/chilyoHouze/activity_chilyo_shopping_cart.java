package com.example.wafil.Wafil.chilyoHouze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class activity_chilyo_shopping_cart extends AppCompatActivity {

    Intent intentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        getSupportActionBar().hide();

        ImageView activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}