package com.example.wafil.Wafil.basBangunan.PesananSedangKerjakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.Wafil.basBangunan.Pesanan_selesai.PesananSelesai;
import com.example.wafil.R;

public class MengerjakanPesanan extends AppCompatActivity {
    Button pesanan_selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mengerjakan_pesanan);

        pesanan_selesai=findViewById(R.id.pesanan_selesai);
        pesanan_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MengerjakanPesanan.this, PesananSelesai.class);
                startActivity(intent);
            }
        });
    }
}
