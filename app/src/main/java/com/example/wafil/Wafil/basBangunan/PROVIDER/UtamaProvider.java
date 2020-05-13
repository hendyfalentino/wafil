package com.example.wafil.Wafil.basBangunan.PROVIDER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.Wafil.basBangunan.PROVIDER.MenermaPesanan.MenerimaPesananProvider;

import com.example.wafil.R;

public class UtamaProvider extends AppCompatActivity {
    Switch mySwitch;
    TextView switchstatus, jikaterimaorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utama_provider);
        jikaterimaorder = findViewById(R.id.jikateromaorder);
        switchstatus = findViewById(R.id.switchStatus);
        mySwitch = findViewById(R.id.Switch);
        //set the switch to ON
        mySwitch.setChecked(true);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    switchstatus.setText("Status: ON");
                } else {
                    switchstatus.setText("Status: OFF");
                }
            }
        });
        //check the current state before we display the screen
        if(mySwitch.isChecked()){
            switchstatus.setText("Status: ON");
        }
        else {
            switchstatus.setText("Status: OFF");
        }
        jikaterimaorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UtamaProvider.this, MenerimaPesananProvider.class);
                startActivity(intent);
            }
        });
    }


}
