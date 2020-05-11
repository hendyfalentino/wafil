package com.example.wafil.Wafil.chilyoHouze.MainServiceAndProduct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SessionManager;
import com.example.wafil.Wafil.chilyoHouze.Functions.AddProductToCart;
import com.example.wafil.Wafil.chilyoHouze.Model.ShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Model.VendorProduct;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.CustomProgressBar;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;
import com.example.wafil.Wafil.print.MapActivity;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {

    /** Class tambahkan ke shopping cart **/
    AddProductToCart addCartHandler;
    Context context;

    /** View element **/
    TextView text_deliver_to_place, text_deliver_to_date;
    TextView main_service_product_name, main_service_product_price, main_service_product_desc, main_service_product_qty, main_service_product_note;
    ImageView add_qty, min_qty, backButton;
    Button main_service_product_add_to_cart;
    ConstraintLayout Product_ChooseLocation, Product_ChooseDate;

    String id_user;
    String product_id;
    int product_price;
    int product_qty;
    SessionManager sessionManager;
    Intent intentSettings;
    String  deliver_to_string_place, deliver_to_date;
    Double deliver_to_lat, deliver_to_long;
    Activity activity_this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_service_product_detail);

        deliver_to_lat = 1.504469;
        deliver_to_long = 124.908277;
        deliver_to_date = "Tanggal";
        deliver_to_string_place = "Lokasi";

        /** mengambil product_id dari activity sebelumnya **/
        Intent intent = getIntent();
        product_id = intent.getStringExtra("product_id");

        /** inisialisasi element **/
        elementInit();
        buttonListener();

        /** mengambil data dari API **/
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user = user.get(SessionManager.user_id);
        getJson(product_id, id_user);
        activity_this = ProductDetail.this;
    }

    private void openLocation(Activity activity){
        Intent intent = new PlacePicker.IntentBuilder()
                .setLatLong(deliver_to_lat, deliver_to_long)
                .showLatLong(true)
                .setMapRawResourceStyle(R.raw.map_style)
                .setMapType(MapType.NORMAL)
                .setPlaceSearchBar(true, "AIzaSyBY8gTcaaQ6tuBkCAMHrfAe_rbpcAIdx1U")
                .build(activity);
        startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);
    }

    private void elementInit(){

        // text view
        main_service_product_name  = findViewById(R.id.main_service_product_name);
        main_service_product_price = findViewById(R.id.main_service_product_price);
        main_service_product_desc  = findViewById(R.id.main_service_product_desc);
        main_service_product_qty   = findViewById(R.id.main_service_product_qty);
        main_service_product_note  = findViewById(R.id.main_service_product_note);
        text_deliver_to_place      = findViewById(R.id.text_deliver_to_place);
        text_deliver_to_date       = findViewById(R.id.text_deliver_to_date);

        // add and min qty button
        add_qty = findViewById(R.id.add_qty);
        min_qty = findViewById(R.id.min_qty);

        // add to cart button
        main_service_product_add_to_cart = findViewById(R.id.main_service_product_add_to_cart);

        // select location and date button
        Product_ChooseLocation = findViewById(R.id.Product_ChooseLocation);
        Product_ChooseDate = findViewById(R.id.Product_ChooseDate);

        Product_ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // back button
        backButton = findViewById(R.id.backButton);
    }

    private void getJson(String product_id, String id_user){
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<VendorProduct> call = service.getSpecificProduct(product_id, id_user);
        call.enqueue(new Callback<VendorProduct>() {
            @Override
            public void onResponse(@NotNull Call<VendorProduct> call, @NotNull Response<VendorProduct> response) {
                if(response.body() != null){
                    product_price            = Integer.parseInt(response.body().getProduct_price());
                    product_qty              = Integer.parseInt(response.body().getProduct_qty());
                    deliver_to_string_place  = response.body().getDeliver_to_string_place();

                    if(response.body().getDeliver_to_lat() != null){
                        deliver_to_lat   = Double.valueOf(response.body().getDeliver_to_lat());
                        deliver_to_long  = Double.valueOf(response.body().getDeliver_to_long());
                    }

                    String lokal_p_price = Support.rupiahFormat(String.valueOf(product_price)) + " koin";
                    main_service_product_name.setText(response.body().getProduct_name());
                    main_service_product_price.setText(lokal_p_price);
                    main_service_product_desc.setText(response.body().getProduct_desc());
                    main_service_product_qty.setText(response.body().getProduct_qty());
                    main_service_product_note.setText(response.body().getProduct_desc());
                    text_deliver_to_place.setText(deliver_to_string_place);
                    text_deliver_to_date.setText(deliver_to_date);

                    // setting data for the first time
                    String total = "Tambahkan - " + String.valueOf(Support.rupiahFormat(String.valueOf(product_qty * product_price))) + " koin";
                    main_service_product_add_to_cart.setText(total);

                    Product_ChooseLocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openLocation(activity_this);
                        }
                    });
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

        /** tambahkan item ke shopping cart
         *  Dpe susunan nmboleh rubah neh hen
         *  Isi jo ini dalam fungsi kong gas
         * **/
        context = this;

        /** set interface **/
        addCartHandler = new AddProductToCart(new AddProductToCart.cartHandler() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(),"Berhasil Ditambahkan :)",Toast.LENGTH_SHORT).show();
                intentSettings = new Intent(ProductDetail.this, ActivityShoppingCart.class);
                finish();
                startActivity(intentSettings);
            }
            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(),"Gagal menambahkan, Silahkan coba lagi",Toast.LENGTH_SHORT).show();
            }
        });

        /** mmenambahkan data ke database
         * addCartHandler.AddItemToCart(context, BUTTON_VIEW[BUTTON ATAU LAYOUT APAPUN ATO NNI PE BUTTON PE ID]);
         * **/
        main_service_product_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note = main_service_product_note.getText().toString().trim();
                addCartHandler.addToCart(context, id_user, product_id, product_qty, product_price, note, deliver_to_lat.toString(), deliver_to_long.toString(), deliver_to_string_place, deliver_to_date);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                try {
                    // ambil address
                    AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);
                    if(addressData != null){
                        deliver_to_lat          = addressData.getLatitude();
                        deliver_to_long         = addressData.getLongitude();
                        deliver_to_string_place = addressData.toString();
                        text_deliver_to_place.setText(deliver_to_string_place);
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}

