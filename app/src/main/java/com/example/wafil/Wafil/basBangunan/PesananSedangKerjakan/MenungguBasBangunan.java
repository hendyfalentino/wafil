package com.example.wafil.Wafil.basBangunan.PesananSedangKerjakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class MenungguBasBangunan extends AppCompatActivity {
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menunggu_bas_bangunan);

        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenungguBasBangunan.this, MengerjakanPesanan.class);
                startActivity(intent);
            }
        });
    }
}
