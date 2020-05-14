package com.example.wafil.Wafil.babySitter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wafil.R;

import java.util.HashMap;
import java.util.Map;

public class CatatanActivity extends AppCompatActivity {

    EditText nama,umur,alamat_rumah,catatan_khusus;
    String str_nama,str_umur,str_alamat_rumah,str_catatan_khusus;
    String url = "https://babysitterppl.000webhostapp.com/catatan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);

        nama = findViewById(R.id.nama);
        umur = findViewById(R.id.umur);
        alamat_rumah = findViewById(R.id.alamat_rumah);
        catatan_khusus = findViewById(R.id.catatan_khusus);
    }

    public void Cari(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if(nama.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan nama buah hati", Toast.LENGTH_SHORT).show();
        }
        else if (umur.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan umur buah hati", Toast.LENGTH_SHORT).show();
        }
        else if(alamat_rumah.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan alamat rumah", Toast.LENGTH_SHORT).show();
        }
        else if(catatan_khusus.getText().toString().equals("")){
            Toast.makeText(this, "Masukan catatan khusus", Toast.LENGTH_SHORT).show();
        }

        else{
            progressDialog.show();
            str_nama = nama.getText().toString().trim();
            str_umur = umur.getText().toString().trim();
            str_alamat_rumah = alamat_rumah.getText().toString().trim();
            str_catatan_khusus = catatan_khusus.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    nama.setText("");
                    umur.setText("");
                    alamat_rumah.setText("");
                    catatan_khusus.setText("");
                    startActivity(new Intent(getApplicationContext(),RecyclerviewActivity.class));
                    Toast.makeText(CatatanActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(CatatanActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("nama",str_nama);
                    params.put("umur",str_umur);
                    params.put("alamat_rumah",str_alamat_rumah);
                    params.put("catatan_khusus",str_catatan_khusus);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(CatatanActivity.this);
            requestQueue.add(request);
        }

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

        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}

