package com.example.wafil.Wafil.bengkel;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class BengkelMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bengkel_main);

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String pass_ =  password.getText().toString();
                if (user_name.equals("admin")&&pass_.equals("admin")){
                    Toast.makeText(getApplicationContext(),"Username dan Password benar Anda berhasil Login",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Username dan Pssword tidak sesuai Anda gagal Login",Toast.LENGTH_SHORT).show();
                }
            }

            private Context getApplicationContext() {
                return null;
            }
        });
    }
}