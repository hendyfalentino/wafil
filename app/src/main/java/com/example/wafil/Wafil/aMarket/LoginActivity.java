package com.example.wafil.Wafil.aMarket;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPass;
    Button ton1;
    TextView tes;
    DataHelper dbhelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_market);

        dbhelper = new DataHelper(this);
        etEmail = findViewById(R.id.editTextEmail);
        etPass = findViewById(R.id.editTextPassword);
        ton1 = findViewById(R.id.buttonLogin);
        tes = findViewById(R.id.tes);

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getReadableDatabase();
                cursor = db.rawQuery("select * from pembeli", null);
                cursor.moveToFirst();
                String user = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                for (int cc = 0; cc < cursor.getCount(); cc++){
                    cursor.moveToPosition(cc);
                    if (cursor.getString(4).toString().equals(user) && cursor.getString(5).toString().equals(pass)){
                        Toast.makeText(LoginActivity.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
                        Intent i1 = new Intent(LoginActivity.this, HomeAdmin.class);
                        startActivity(i1);
                        return;
                    } else if (user.equals("") && pass.equals("")){
                        Toast.makeText(LoginActivity.this, "Tolong Masukkan Nama Pengguna Dan Kata Sandi", Toast.LENGTH_SHORT).show();
                    }else if (user.equals("")) {
                        Toast.makeText(LoginActivity.this, "Tolong Masukkan Nama Pengguna", Toast.LENGTH_SHORT).show();
                    }else if (pass.equals("")) {
                        Toast.makeText(LoginActivity.this, "Tolong Masukkan Sandi", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Tolong Isi Nama Pengguna Dan Sandi Yang Benar", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });

    }
}
