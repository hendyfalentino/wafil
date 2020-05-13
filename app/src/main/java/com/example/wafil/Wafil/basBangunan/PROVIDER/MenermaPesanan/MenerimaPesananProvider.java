package com.example.wafil.Wafil.basBangunan.PROVIDER.MenermaPesanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class MenerimaPesananProvider extends AppCompatActivity {
    Button terimapesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menerima_pesanan_provider);

        terimapesanan = findViewById(R.id.terima_pesanan);
        terimapesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenerimaPesananProvider.this, PesananDiterima.class);
                startActivity(intent);
            }
        });

    }


}
