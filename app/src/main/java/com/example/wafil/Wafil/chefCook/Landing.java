package com.example.wafil.Wafil.chefCook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class Landing extends AppCompatActivity {

    private Button mButton1, mButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mButton1 = findViewById(R.id.bttregis);
        mButton2 = findViewById(R.id.bttlogin);


    }
}
