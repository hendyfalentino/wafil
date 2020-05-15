package com.example.wafil.Wafil.atorJo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wafil.R;
import android.os.Bundle;
import android.view.View;

public class atorjoHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atorjo_history);

        getJson();

        elementIninit();
    }

    private void getJson() {
    }

    private void elementIninit() {
    }


}
