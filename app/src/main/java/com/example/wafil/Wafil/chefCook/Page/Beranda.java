package com.example.wafil.Wafil.chefCook.Page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.chefCook.AdminDashboard;
import com.example.wafil.Wafil.chefCook.Landing;


public class Beranda extends AppCompatActivity {

    Button mButt;
    Button home, order, akun;
    Button mAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        mButt = findViewById(R.id.bttini);
        mButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Landing.class));
                finish();
            }
        });

        home = findViewById(R.id.btHome);
        order = findViewById(R.id.btOrder);
        akun = findViewById(R.id.btAccount);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Beranda.class));
                finish();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Order.class));
                //finish();
            }
        });
        akun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Account.class));
                //finish();
            }
        });

        mAdmin = findViewById(R.id.btAdmin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(intent);
//                finish();
            }
        });

    }
}
