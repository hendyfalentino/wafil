package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class DaftarpekerjaanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarpekerjaan);
    }
    public void on(View view) {

        startActivity(new Intent(getApplicationContext(), DetailjobActivity.class));
        finish();
    }
    public void Beranda(View view) {

        startActivity(new Intent(getApplicationContext(), BerandabbsActivity.class));
        finish();
    }
}
