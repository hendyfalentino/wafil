package com.example.wafil.Wafil.chefCook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.wafil.R;

public class AdminDashboard extends AppCompatActivity {

    CardView card1, card2, card3, card4, card5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        card2 = findViewById(R.id.bt_card2);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Card 2 ", Toast.LENGTH_SHORT).show();
            }
        });
        card3 = findViewById(R.id.bt_card3);
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Card 3 ", Toast.LENGTH_SHORT).show();
            }
        });
        card4 = findViewById(R.id.bt_card4);
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Card 4 ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
