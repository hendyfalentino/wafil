package com.example.wafil.Wafil.chefCook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.wafil.R;
import com.example.wafil.Wafil.chefCook.Page.Account;
import com.example.wafil.Wafil.chefCook.Page.Beranda;
import com.example.wafil.Wafil.chefCook.Page.Order;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChefMainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    View hostFragment;
    NavController navController;

    Button home, order, akun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chefcook_main);
        home = findViewById(R.id.btHome);
        order = findViewById(R.id.btOrder);
        akun = findViewById(R.id.btAccount);

        home.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Beranda.class));
            //finish();
        });
        order.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Order.class));
            //finish();
        });
        akun.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Account.class));
            //finish();
        });

//        type4
        bottomNavigationView = findViewById(R.id.bottomNavView);
        navController = Navigation.findNavController(this, R.id.fragmentHost);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        type3
//        bottomNavigationView= findViewById(R.id.bottomNavView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Fragment select = null;
//
//                switch (menuItem.getItemId()){
//                    case R.id.fr_beranda :
//                        select = new BerandaFrag();
//                        break;
//                    case R.id.fr_order :
//                        select = new OrderFrag();
//                        break;
//                    case R.id.fr_akun :
//                        select = new AkunFrag();
//                        break;
//
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHost, select).commit();
//
//                return true;
//            }
//        });

//        type2
//        bottomNavigationView.findViewById(R.id.bottomNavView);
//        navhost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_menu_frag);
//        NavigationUI.setupWithNavController(bottomNavigationView, navhost.getNavController());

//        type1
//        navController = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_menu_frag);
//        bottomNavigationView = findViewById(R.id.bottomNavView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fr_beranda)
//                .commit();

    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navlistener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                    Fragment select = null;
//
//                    switch (menuItem.getItemId()){
//                        case R.id.fr_beranda :
//                            select = new BerandaFrag();
//                            break;
//                        case R.id.fr_order:
//                            select = new OrderFrag();
//                            break;
//                        case R.id.fr_akun:
//                            select = new AkunFrag();
//                            break;
//
//                    }
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragmentHost, select)
//                            .commit();
//
//                    return true;
//                }
//            };
}
