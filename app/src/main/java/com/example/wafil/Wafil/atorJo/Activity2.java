package com.example.wafil.Wafil.atorJo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class Activity2 extends AppCompatActivity {
    private Button buttonRumah;
    private Button buttonKantor;
    private Button buttonPengemas;
    //Comment by Keny Test Commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //getSupportActionBar().setTitle("atorJo");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonRumah = (Button) findViewById(R.id.buttonRumah);
        buttonRumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonRumah();
            }
        });

        buttonKantor = (Button) findViewById(R.id.buttonKantor);
        buttonKantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonKantor();
            }
        });

        buttonPengemas = (Button) findViewById(R.id.buttonPengemas);
        buttonPengemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonPengemas();
            }
        });
    }

    public void setButtonRumah(){
        Intent intent = new Intent(this, bersihRumah.class);
        startActivity(intent);
    }

    public void setButtonKantor(){
        Intent intent = new Intent(this, bersihKantor.class);
        startActivity(intent);
    }

    public void setButtonPengemas(){
        Intent intent = new Intent(this, pengemasBarang.class);
        startActivity(intent);
    }
}
