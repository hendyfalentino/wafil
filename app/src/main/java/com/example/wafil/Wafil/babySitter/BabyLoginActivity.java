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
import com.example.wafil.Wafil.API.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class BabyLoginActivity extends AppCompatActivity {


    EditText email,password;
    String str_email,str_password;
    String url = "https://babysitterppl.000webhostapp.com/UserRegistration/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_baby);


        sessionManager = new SessionManager(getApplicationContext());

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void Masuk(View view) {

        if(email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();

            str_email = email.getText().toString().trim();
            str_password = password.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if(response.equalsIgnoreCase("login success")){
                        email.setText("");
                        password.setText("");
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        Toast.makeText(BabyLoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(BabyLoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(BabyLoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("email",str_email);
                    params.put("password",str_password);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(BabyLoginActivity.this);
            requestQueue.add(request);
        }
    }
    public void daftar(View view) {
        startActivity(new Intent(getApplicationContext(),DaftarActivity.class));
        finish();
    }
    public void Pengasuh(View view) {
        startActivity(new Intent(getApplicationContext(),Daftarbs1Activity.class));
        finish();
    }
    public void babysitter(View view) {
        startActivity(new Intent(getApplicationContext(),LoginbbsActivity.class));
        finish();
    }
    public void admin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginadminActivity.class));
        finish();
    }
}
