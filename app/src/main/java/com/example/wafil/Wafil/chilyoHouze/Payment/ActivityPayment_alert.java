package com.example.wafil.Wafil.chilyoHouze.Payment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.wafil.R;
import com.google.android.gms.common.internal.SignInButtonImpl;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPayment_alert extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_chilyo_payment);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(ActivityPayment_alert.this);
        dialog.setContentView(R.layout.alert_dialog);

        final CheckBox alert_checkbox = dialog.findViewById(R.id.alert_checkbox);
        final Button button_batal = dialog.findViewById(R.id.button_batal);
        final Button button_ok = dialog.findViewById(R.id.button_ok);

        button_batal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.cancel();
            }
        });

        button_ok.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.dismiss();
            }
        });

        alert_checkbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (alert_checkbox.isChecked()){
                    button_ok.setEnabled(true);
                    button_ok.setBackgroundColor(getResources().getColor(android.R.color.background_light));
                }else{
                    button_ok.setEnabled(false);
                    button_ok.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
                }
            }
        });
        dialog.show();
    }
}
