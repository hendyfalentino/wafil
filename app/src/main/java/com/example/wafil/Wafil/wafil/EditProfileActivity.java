package com.example.wafil.Wafil.wafil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private TextView user_name;
    private EditText user_first_name;
    private Button btn_save_profile;
    public SessionManager sessionManager;
    String getUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user_name = findViewById(R.id.show_user_name);
        user_first_name = findViewById(R.id.edit_show_first_name);
        btn_save_profile = findViewById(R.id.btn_save_profile);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogIn();

        HashMap<String, String> user = sessionManager.getUserDetail();
        getUserId = user.get(SessionManager.user_id);
        btn_save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_profile();
            }
        });
    }

    private void getUserDetail(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SessionManager.url+"read_profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.i(TAG, response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("read");

                    if(success.equals("1")){
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            String strUser_name = object.getString("user_name").trim();
                            String strFirst_name = object.getString("user_first_name").trim();

                             user_name.setText(strUser_name);
                             user_first_name.setText(strFirst_name);
                        }
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Can't Get Profile"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EditProfileActivity.this, "Can't Get Profile"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", getUserId);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    private void save_profile() {
        final String user_first_name = this.user_first_name.getText().toString().trim();
        final String user_name = this.user_name.getText().toString().trim();
        final String user_id = getUserId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SessionManager.url+"save_profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")){
                        Toast.makeText(EditProfileActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        sessionManager.createSession(user_first_name, user_name, user_id);
                        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    Toast.makeText(EditProfileActivity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(EditProfileActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", user_name);
                params.put("user_first_name", user_first_name);
                params.put("user_id", user_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
