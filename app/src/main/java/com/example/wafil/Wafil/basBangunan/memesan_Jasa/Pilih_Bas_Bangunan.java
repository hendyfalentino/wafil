package com.example.wafil.Wafil.basBangunan.memesan_Jasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.Wafil.basBangunan.PesananSedangKerjakan.MenungguBasBangunan;
import com.example.wafil.R;

public class Pilih_Bas_Bangunan extends AppCompatActivity {
    Button sewatukang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_bas_bangunan);
        sewatukang=findViewById(R.id.sewa_tukang);
        sewatukang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Pilih_Bas_Bangunan.this, MenungguBasBangunan.class);
                startActivity(intent);
            }
        });
    }
}
