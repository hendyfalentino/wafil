package com.example.wafil.Wafil.chefCook.Page;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.wafil.R;
import com.example.wafil.Wafil.chefCook.Landing;


public class Account extends AppCompatActivity {

    Button buttonini;
    CardView cardViewini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        buttonini = findViewById(R.id.bttini);
        buttonini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Landing.class);
                startActivity(intent);
                finish();
            }
        });

        cardViewini = findViewById(R.id.cardaccount);
        cardViewini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Account_profile.class);
                startActivity(intent);
            }
        });


    }
}
