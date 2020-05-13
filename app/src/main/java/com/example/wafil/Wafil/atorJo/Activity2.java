package com.example.wafil.Wafil.atorJo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class Activity2 extends AppCompatActivity {

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

    }
}
