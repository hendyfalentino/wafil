package com.example.wafil.Wafil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView txt_login;
    private EditText user_first_name, user_name, user_password, c_user_password;
    private Button btn_signup;
    private ProgressBar loading;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    //"(?=\\S+$)" +           //no white spaces
                    ".{8,}" );               //at least 4 characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_first_name = findViewById(R.id.user_first_name);
        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        c_user_password = findViewById(R.id.c_user_password);
        btn_signup = findViewById(R.id.btn_signup);
        txt_login = findViewById(R.id.txt_login);

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mFName = user_first_name.getText().toString().trim();
                String mName = user_name.getText().toString().trim();
                String mPass = user_password.getText().toString().trim();
                String mCPass= c_user_password.getText().toString().trim();

                if(mFName.isEmpty()){
                    user_first_name.setError("Insert Username");
                } else {
                    user_first_name.setError(null);
                }
                if(mName.isEmpty()){
                    user_name.setError("Insert Username");
                } else {
                    user_name.setError(null);
                }
                if(!mName.matches(emailPattern)){
                    user_name.setError("Insert Email");
                } else {
                    user_name.setError(null);
                }
                if(mPass.isEmpty()){
                    user_password.setError("Insert Password");
                } else {
                    user_password.setError(null);
                }
                if(!PASSWORD_PATTERN.matcher(mPass).matches()) {
                    user_password.setError("Password too weak");
                } else {
                    user_password.setError(null);
                }
                if(mCPass.isEmpty()){
                    c_user_password.setError("Confirm Password");
                } else {
                    c_user_password.setError(null);
                }
                if(!mPass.equals(mCPass) || mCPass.isEmpty()){
                    c_user_password.setError("Password Not Match");
                } else {
                    c_user_password.setError(null);
                }

                if(!mFName.isEmpty() && !mName.isEmpty() && !mPass.isEmpty() && !mCPass.isEmpty() &&PASSWORD_PATTERN.matcher(mPass).matches()  && mPass.equals(mCPass)){
                    signup(mFName, mName, mPass, mCPass);
                }
            }
        });

    }

    private void signup(String mFName, String mName, String mPass, String mCPass){

        final String user_first_name = this.user_first_name.getText().toString().trim();
        final String user_name = this.user_name.getText().toString().trim();
        final String user_password = this.user_password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SessionManager.url+"signUp.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if (success.equals("1")){
                            Toast.makeText(RegisterActivity.this, "Register Sucess!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    } catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_first_name", user_first_name);
                params.put("user_name", user_name);
                params.put("user_password", user_password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
