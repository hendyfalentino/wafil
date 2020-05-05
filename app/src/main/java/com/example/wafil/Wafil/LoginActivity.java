package com.example.wafil.Wafil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.wafil.Wafil.API.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView txt_register;
    private EditText user_name, user_password;
    private Button btn_login;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        user_name = findViewById(R.id.user_name);
        user_password = findViewById(R.id.user_password);
        btn_login = findViewById(R.id.btn_login);
        txt_register = findViewById(R.id.txt_register);

        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = user_name.getText().toString().trim();
                String mPass = user_password.getText().toString().trim();

                if (mName.isEmpty()){
                    user_name.setError("Please Insert Username");
                }
                if (mPass.isEmpty()){
                    user_name.setError("Please Insert Passsword");
                }
                if(!mName.isEmpty() || !mPass.isEmpty()) {
                    Login(mName, mPass);
                }
            }
        });
    }

    private void Login(final String user_name, final String user_password){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SessionManager.url+"signIn.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");

                    if(success.equals("1")){
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String user_first_name = object.getString("user_first_name").trim();
                            String user_name = object.getString("user_name").trim();
                            String user_id = object.getString("user_id").trim();

                            sessionManager.createSession(user_first_name, user_name, user_id);

                            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                            intent.putExtra("user_name", user_name);
                            intent.putExtra("user_first_name", user_first_name);
                            intent.putExtra("user_id", user_id);
                            startActivity(intent);
                            finish();
                        }
                    }
                } catch (
                        JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", user_name);
                params.put("user_password", user_password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
