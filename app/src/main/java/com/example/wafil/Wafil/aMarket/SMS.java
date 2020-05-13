package com.example.wafil.Wafil.aMarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SMS extends AppCompatActivity {
    private EditText notel, sms;
    private Button tonS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        nav();

        notel = (EditText) findViewById(R.id.ets1);
        sms = (EditText) findViewById(R.id.sms);
        tonS = (Button) findViewById(R.id.kirimsms);

        tonS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                kirim();
            }
        });

    }

    public void kirim() {
        Uri uri = Uri.parse("smsto:" + notel.getText().toString());

        Intent intent1 = new Intent(Intent.ACTION_SENDTO, uri);
        intent1.putExtra("sms_body", sms.getText().toString());
        try{
            startActivity(intent1);
        } catch (Exception ex) {
            Toast.makeText(SMS.this, "Pengiriman SMS Gagal",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    public void nav(){
        BottomNavigationView botnav = findViewById(R.id.botnvbar);
        Menu menu = botnav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent i1 = new Intent(SMS.this, HomeAdmin.class);
                        startActivity(i1);
                        break;
                    case R.id.ic_kb:
                        Intent i2 = new Intent(SMS.this, TambahBarang.class);
                        startActivity(i2);
                        break;
                    case R.id.ic_out:
                        Intent i3 = new Intent(SMS.this, LoginActivity.class);
                        startActivity(i3);
                        break;
                }

                return false;
            }
        });
    }
}
