package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }
    public void Backhome(View view) {

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }
}
