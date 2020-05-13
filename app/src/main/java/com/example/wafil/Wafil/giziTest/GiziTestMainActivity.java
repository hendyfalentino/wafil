package com.example.wafil.Wafil.giziTest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wafil.R;

import okhttp3.OkHttpClient;


public class GiziTestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gizi_test_main);

        /* Stetho */
       /* Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build(); */

        /* Database */
        DBAdapter db = new DBAdapter(this);
        db.open();

        /* Setup for food */
        // Count rows in food
        int numberRows = db.count("food");

        if(numberRows < 1){
            // Run setup
            // Toast.makeText(this, "Loading setup...", Toast.LENGTH_LONG).show();
            DBSetupInsert setupInsert = new DBSetupInsert(this);
            setupInsert.insertAllCategories();
            setupInsert.insertAllFood();
            // Toast.makeText(this, "Setup completed!", Toast.LENGTH_LONG).show();

        }

        /* Check if there is user in the user table */
        // Count rows in user table
        numberRows = db.count("users");

        /* Close database */
        db.close();

        if(numberRows < 1){
            // Sign up
            // Toast.makeText(this, "You are only few fields away from signing up...", Toast.LENGTH_LONG).show();

            Intent i = new Intent(GiziTestMainActivity.this, SignUp.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(GiziTestMainActivity.this, FragmentActivity.class);
            startActivity(i);

        }

    }

}
