package com.example.wafil.Wafil;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        sessionManager.LogOut();
    }
}

