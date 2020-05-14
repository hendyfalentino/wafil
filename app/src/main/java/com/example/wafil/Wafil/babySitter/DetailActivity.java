package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.wafil.R;

import static com.example.wafil.Wafil.babySitter.UserActivity.EXTRA_EMAIL;
import static com.example.wafil.Wafil.babySitter.UserActivity.EXTRA_IMAGE;
import static com.example.wafil.Wafil.babySitter.UserActivity.EXTRA_NAME;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        String image = intent.getStringExtra(EXTRA_IMAGE);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewName = findViewById(R.id.name);
        TextView textViewEmail = findViewById(R.id.email);

        textViewName.setText(name);
        textViewEmail.setText(email);
        Glide.with(this).load(image).fitCenter().into(imageView);
    }
    public void Profile(View view) {

        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
    }
    public void History(View view) {

        startActivity(new Intent(getApplicationContext(), RiwayatActivity.class));
        finish();
    }
    public void Ongoing(View view) {

        startActivity(new Intent(getApplicationContext(), PesananActivity.class));
        finish();
    }
    public void Home(View view) {

        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
    public void Backhome(View view) {

        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        finish();
    }
    public void Pesan(View view) {

        startActivity(new Intent(getApplicationContext(), HargaActivity.class));
        finish();
    }

}
