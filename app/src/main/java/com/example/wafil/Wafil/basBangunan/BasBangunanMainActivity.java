package com.example.wafil.Wafil.basBangunan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.wafil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class BasBangunanMainActivity extends AppCompatActivity {
    TextView txthello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bas_bangunan_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beranda, R.id.navigation_pesanan, R.id.navigation_akun)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        /*NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

         */
        NavigationUI.setupWithNavController(navView, navController);
    }
}