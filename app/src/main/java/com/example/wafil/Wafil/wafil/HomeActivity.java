package com.example.wafil.Wafil.wafil;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {


    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

        sessionManager = new SessionManager(this);
        sessionManager.checkLogIn();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    HashMap<String, String> user = sessionManager.getUserDetail();
                    Bundle bundle = new Bundle();
                    String mName = user.get(sessionManager.user_name);
                    String mFName = user.get(sessionManager.user_first_name);
                    bundle.putString("user_name", mName);
                    bundle.putString("user_first_name", mFName);
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_setting:
                            selectedFragment = new SettingFragment();
                            selectedFragment.setArguments(bundle);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}
