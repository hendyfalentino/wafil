package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class BerandabbsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berandabbs);
    }
    public void lanjut(View view) {

        startActivity(new Intent(getApplicationContext(), DaftarpekerjaanActivity.class));
        finish();
    }
}
