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
import com.example.wafil.Wafil.atorJo.Activity2;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_main;
import com.example.wafil.Wafil.wafil.CategoryProductActivity;
import com.example.wafil.Wafil.wafil.ServiceTypeActivity;

public class MainMenuActivity extends Fragment {

    Button btn_wafil, btn_chilyo, button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_menu, container, false);
        btn_wafil = v.findViewById(R.id.btn_wafil);
        btn_chilyo = v.findViewById(R.id.btn_chilyo);
        button = v.findViewById(R.id.button);
        btn_wafil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ServiceTypeActivity.class);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Activity2.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
