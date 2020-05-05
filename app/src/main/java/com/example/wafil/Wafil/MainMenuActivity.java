package com.example.wafil.Wafil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouse.activity_chilyo_main;
import com.example.wafil.Wafil.wafil.CategoryProductActivity;

public class MainMenuActivity extends AppCompatActivity {

    SessionManager sessionManager;
    Button btn_wafil, btn_chilyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_menu);
        setContentView(R.layout.activity_main_menu);

        btn_wafil = findViewById(R.id.btn_wafil);
        btn_chilyo = findViewById(R.id.btn_chilyo);
        btn_wafil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, CategoryProductActivity.class);
                startActivity(intent);
            }
        });

        btn_chilyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, activity_chilyo_main.class);
                startActivity(intent);
            }
        });
        sessionManager = new SessionManager(this);
        sessionManager.checkLogIn();
    }
}
