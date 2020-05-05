package com.example.wafil.Wafil.wafil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.LoginActivity;
import com.example.wafil.Wafil.MenuActivity;

public class MainActivity extends AppCompatActivity {

    private int time = 4000;
    SessionManager sessionManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);

        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sessionManager.isLogIn()){
                    finish();
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },time);
    }
}
