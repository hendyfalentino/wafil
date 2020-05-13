package com.example.wafil.Wafil.basBangunan.ui.Akun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfil extends AppCompatActivity {
    EditText notelepon, name, email, jeniskelamin, alamat;
    Button simpan;
    String strphone, strname, stremail, strjeniskel, stralamat;
    String url = "https://fragmentary-ices.000webhostapp.com/profil1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);


        name = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        notelepon = findViewById(R.id.nomortlpn);
        jeniskelamin= findViewById(R.id.jeniskelamin);
        alamat = findViewById(R.id.alamat);

        simpan = findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_data();
            }
        });
    }
    void edit_data(){
        String url = "https://fragmentary-ices.000webhostapp.com/edit_data_user.php";
        StringRequest respon  = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("Data_Update"))
                            {
                                AlertDialog.Builder builder = new  AlertDialog.Builder(EditProfil.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Update data berhasil");
                                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        finish();
                                    }
                                });
                                AlertDialog dialog=builder.create();
                                dialog.show();

                            }
                            else {
                                AlertDialog.Builder builder = new  AlertDialog.Builder(EditProfil.this);
                                builder.setTitle("Sukses");
                                builder.setMessage("Update data gagal");
                                builder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                    }
                                });
                                AlertDialog dialog=builder.create();
                                dialog.show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> form = new HashMap<>();
                form.put("id", getIntent().getStringExtra("id"));
                form.put("name", name.getText().toString());
                form.put("email", email.getText().toString());
                form.put("notelepon", notelepon.getText().toString());
                form.put("jeniskelamin", jeniskelamin.getText().toString());
                form.put("alamat", alamat.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(respon);
    }
}
