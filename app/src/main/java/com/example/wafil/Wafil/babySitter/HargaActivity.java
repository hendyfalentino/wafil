package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class HargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harga);
    }
    public void Profile(View view) {

        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
    }
    public void History(View view) {

        startActivity(new Intent(getApplicationContext(), RiwayatActivity.class));
        finish();
    }
    public void Ongoing(View view) {

        startActivity(new Intent(getApplicationContext(), PesananActivity.class));
        finish();
    }
    public void Home(View view) {

        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
    public void Backhome(View view) {

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }
    public void Order(View view) {

        startActivity(new Intent(getApplicationContext(), FinishActivity.class));
        finish();
    }
}
