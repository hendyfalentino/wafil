package com.example.wafil.Wafil.chilyoHouze;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.chilyoHouze.Payment.ActivityPayment;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;

public class activity_chilyo_history extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_history);
        getJson();

        elementInit();

    }

    private void getJson() {
    }

    private void elementInit() {
    }

}
