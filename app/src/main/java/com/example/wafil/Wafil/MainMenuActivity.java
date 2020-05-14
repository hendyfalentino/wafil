package com.example.wafil.Wafil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wafil.R;
import com.example.wafil.Wafil.aMarket.HomeAdmin;
import com.example.wafil.Wafil.atorJo.Activity2;
import com.example.wafil.Wafil.babySitter.BabyLoginActivity;
import com.example.wafil.Wafil.basBangunan.BasBangunanMainActivity;
import com.example.wafil.Wafil.bengkel.BengkelMainActivity;
import com.example.wafil.Wafil.chefCook.ChefMainActivity;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_main;
import com.example.wafil.Wafil.giziTest.SignUp;
import com.example.wafil.Wafil.print.MapActivity;
import com.example.wafil.Wafil.wafil.ServiceTypeActivity;

public class MainMenuActivity extends Fragment {

    Button btn_wafil, btn_print, btn_chilyo, btn_atorjo, btn_basbangunan, btn_gizitest, btn_market,
            btn_chefcook, btn_benkel, btn_babysitter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_menu, container, false);

        btn_wafil = v.findViewById(R.id.btn_wafil);
        btn_print = v.findViewById(R.id.btn_print);
        btn_chilyo = v.findViewById(R.id.btn_chilyo);
        btn_atorjo = v.findViewById(R.id.btn_atorjo);
        btn_basbangunan = v.findViewById(R.id.btn_basbangunan);
        btn_gizitest = v.findViewById(R.id.btn_gizitest);
        btn_market = v.findViewById(R.id.btn_market);
        btn_chefcook = v.findViewById(R.id.btn_chefcook);
        btn_benkel = v.findViewById(R.id.btn_bengkel);
        btn_babysitter = v.findViewById(R.id.btn_babysitter);

        btn_wafil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceTypeActivity.class);
                startActivity(intent);
            }
        });
        btn_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        btn_chilyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_chilyo_main.class);
                startActivity(intent);
            }
        });
        btn_atorjo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity2.class);
                startActivity(intent);
            }
        });
        btn_basbangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BasBangunanMainActivity.class);
                startActivity(intent);
            }
        });
        btn_gizitest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUp.class);
                startActivity(intent);
            }
        });
        btn_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeAdmin.class);
                startActivity(intent);
            }
        });
        btn_chefcook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChefMainActivity.class);
                startActivity(intent);
            }
        });
        btn_benkel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BengkelMainActivity.class);
                startActivity(intent);
            }
        });
        btn_babysitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BabyLoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
