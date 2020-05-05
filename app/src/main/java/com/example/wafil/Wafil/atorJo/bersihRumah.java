package com.example.wafil.Wafil.atorJo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class bersihRumah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bersih_rumah);

        //getSupportActionBar().setTitle("Bersih Rumah");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = findViewById(R.id.button_lanjut);
        final RadioButton denganAlat = findViewById(R.id.radio_dengan_alat);
        final RadioButton tanpaAlat = findViewById(R.id.radio_tanpa_alat);
        final RadioButton induvidu = findViewById(R.id.radio_individual);
        final RadioButton grup = findViewById(R.id.radio_grup);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (denganAlat.isChecked()){
                    Toast.makeText(bersihRumah.this, "Dengan Alat dipilih", Toast.LENGTH_LONG).show();
                    return;
                }

                if (tanpaAlat.isChecked()){
                    Toast.makeText(bersihRumah.this,"Tanpa Alat dipilih", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    Toast.makeText(bersihRumah.this,"Silahkan Memilih", Toast.LENGTH_LONG).show();
                }

                if (induvidu.isChecked()){
                    Toast.makeText(bersihRumah.this, "Idndividu dipilih", Toast.LENGTH_LONG).show();
                    return;
                }

                if (grup.isChecked()){
                    Toast.makeText(bersihRumah.this,"Grup dipilih", Toast.LENGTH_LONG).show();
                    return;

                }

                else {
                    Toast.makeText(bersihRumah.this, "Silahkan Memilih", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
