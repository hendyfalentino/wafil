package com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorProduct;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {

    TextView main_service_product_name, main_service_product_price, main_service_product_desc, main_service_product_qty, main_service_product_note;
    ImageView add_qty, min_qty, backButton;
    Button main_service_product_add_to_cart;
    Context context;
    String id_user;
    String product_id;
    CustomProgressBar progress;
    int product_price;
    int product_qty;
    SessionManager sessionManager;
    Intent intentSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_service_product_detail);
        getSupportActionBar().hide();

        /** mengambil product_id dari activity sebelumnya **/
        Intent intent = getIntent();
        product_id = intent.getStringExtra("product_id");

        /** inisialisasi element **/
        elementInit();
        buttonListener();

        /** mengambil data dari API **/

        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(SessionManager.user_id);
        getJson(product_id, id_user);
    }

    private void elementInit(){

        // text view
        main_service_product_name  = findViewById(R.id.main_service_product_name);
        main_service_product_price = findViewById(R.id.main_service_product_price);
        main_service_product_desc  = findViewById(R.id.main_service_product_desc);
        main_service_product_qty   = findViewById(R.id.main_service_product_qty);
        main_service_product_note   = findViewById(R.id.main_service_product_note);

        // add and min qty button
        add_qty = findViewById(R.id.add_qty);
        min_qty = findViewById(R.id.min_qty);

        // add to cart button
        main_service_product_add_to_cart = findViewById(R.id.main_service_product_add_to_cart);

        // back button
        backButton = findViewById(R.id.backButton);
    }

    private void getJson(String product_id, String id_user){
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<VendorProduct> call = service.getSpecificProduct(product_id, id_user);
        call.enqueue(new Callback<VendorProduct>() {
            @Override
            public void onResponse(Call<VendorProduct> call, Response<VendorProduct> response) {
                if(response.body() != null){
                    product_price = Integer.parseInt(response.body().getProduct_price());
                    product_qty   = Integer.parseInt(response.body().getProduct_qty());
                    String lokal_p_price = Support.rupiahFormat(String.valueOf(product_price)) + " koin";

                    main_service_product_name.setText(response.body().getProduct_name());
                    main_service_product_price.setText(lokal_p_price);
                    main_service_product_desc.setText(response.body().getProduct_desc());
                    main_service_product_qty.setText(response.body().getProduct_qty());
                    main_service_product_note.setText(response.body().getProduct_note());

                    // setting data for the first time
                    String total = "Tambahkan - " + String.valueOf(Support.rupiahFormat(String.valueOf(product_qty * product_price))) + " koin";
                    main_service_product_add_to_cart.setText(total);
                }
            }

            @Override
            public void onFailure(Call<VendorProduct> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void buttonListener(){

        /** tombol +1 item **/
        add_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                product_qty = product_qty + 1;
                main_service_product_qty.setText(String.valueOf(product_qty));

                /** Menghitung kembali total harga dari keranjang **/
                String total = "Tambahkan - " + String.valueOf(Support.rupiahFormat(String.valueOf(product_qty * product_price))) + " koin";
                main_service_product_add_to_cart.setText(total);
            }
        });

        /** tombol -1 item **/
        min_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // validation, item can't go lower than 1
                if(product_qty > 1){
                    product_qty = product_qty - 1;
                }

                main_service_product_qty.setText(String.valueOf(product_qty));

                /** Menghitung kembali total harga dari keranjang **/
                String total = "Tambahkan - " + String.valueOf(Support.rupiahFormat(String.valueOf(product_qty * product_price))) + " koin";
                main_service_product_add_to_cart.setText(total);
            }
        });

        /** tombol kembali **/
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        progress = new CustomProgressBar();

        /** tambahkan item ke shopping cart **/
        main_service_product_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                String note = main_service_product_note.getText().toString().trim();
                ApiInterface cart = ApiClient.getRetrofitInstance().create(ApiInterface.class);
                Call<ShoppingCart> call = cart.addToCart(id_user, product_id, product_qty, product_price, note);
                call.enqueue(new Callback<ShoppingCart>() {
                    @Override
                    public void onResponse(Call<ShoppingCart> call, Response<ShoppingCart> response) {
                        Log.d("getData", String.valueOf(response.isSuccessful()));
                        Log.d("getData", String.valueOf(response.message()));

                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Berhasil Ditambahkan :)",Toast.LENGTH_SHORT).show();
                            intentSettings = new Intent(ProductDetail.this, ActivityShoppingCart.class);
                            finish();
                            startActivity(intentSettings);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Gagal menambahkan, Silahkan coba lagi",Toast.LENGTH_SHORT).show();
                            dismissProgress();
                        }
                    }

                    @Override
                    public void onFailure(Call<ShoppingCart> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Gagal menambahkan, Silahkan coba lagi",Toast.LENGTH_SHORT).show();
                        dismissProgress();
                        Log.d("getData", t.toString());
                    }
                });
            }
        });
    }

    private void showProgress(){
        progress.show(this, "Mohon tunggu");
    }

    private void dismissProgress(){
        progress.getDialog().dismiss();
    }
}

