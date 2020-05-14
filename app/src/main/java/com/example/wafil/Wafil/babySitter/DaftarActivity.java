package com.example.wafil.Wafil.babySitter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wafil.R;
import com.example.wafil.Wafil.basBangunan.memesan_Jasa.openCamera.helper.PermissionHelper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DaftarActivity extends AppCompatActivity {

    Button btnSelectImage;
    ImageView imageView;
    Bitmap bitmap;
    EditText id,name,email,password,address,phone_number,gender;
    String str_id,str_name,str_email,str_password,str_phone_number,str_address,str_gender,encodedImage;
    String url = "https://babysitterppl.000webhostapp.com/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        id = findViewById(R.id.id);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        phone_number = findViewById(R.id.phone_number);
        gender = findViewById(R.id.gender);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        imageView = findViewById(R.id.imageView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(DaftarActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){

            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

                imageStore(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

    public void Login(View view) {

        startActivity(new Intent(getApplicationContext(),BabyLoginActivity.class));
        finish();
    }

    public void Register(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");


        if(id.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan id", Toast.LENGTH_SHORT).show();
        }
        else if (name.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan nama", Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan Email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(this, "Masukan Kata sandi", Toast.LENGTH_SHORT).show();
        }
        else if (address.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan alamat", Toast.LENGTH_SHORT).show();
        }
        else if (phone_number.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan nomor hp", Toast.LENGTH_SHORT).show();
        }
        else if (gender.getText().toString().equals("")){
            Toast.makeText(this, "Masukkan Jenis kelamin", Toast.LENGTH_SHORT).show();
        }

        else{

            progressDialog.show();
            str_id = id.getText().toString().trim();
            str_name = name.getText().toString().trim();
            str_email = email.getText().toString().trim();
            str_password = password.getText().toString().trim();
            str_address = address.getText().toString().trim();
            str_phone_number = phone_number.getText().toString().trim();
            str_gender = gender.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    id.setText("");
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    address.setText("");
                    phone_number.setText("");
                    gender.setText("");
                    startActivity(new Intent(getApplicationContext(),BabyLoginActivity.class));
                    Toast.makeText(DaftarActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(DaftarActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    params.put("id",str_id);
                    params.put("name",str_name);
                    params.put("email",str_email);
                    params.put("password",str_password);
                    params.put("address",str_address);
                    params.put("phone_number",str_phone_number);
                    params.put("gender",str_gender);
                    params.put("image", encodedImage);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(DaftarActivity.this);
            requestQueue.add(request);

        }

    }
}
