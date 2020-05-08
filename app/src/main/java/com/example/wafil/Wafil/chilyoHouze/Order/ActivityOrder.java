package com.example.wafil.Wafil.chilyoHouze.Order;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.API.ApiClient;
import com.example.wafil.Wafil.API.ApiInterface;
import com.example.wafil.Wafil.API.SharedPreferencesStore;
import com.example.wafil.Wafil.chilyoHouze.Adapters.OrderGetItemAdapter;
import com.example.wafil.Wafil.chilyoHouze.Adapters.PaymentGetItemAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.OrderItem;
import com.example.wafil.Wafil.chilyoHouze.Model.PaymentItem;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_order;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_rating;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_topup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOrder extends AppCompatActivity {

    RecyclerView rv_orderItem;
    OrderGetItemAdapter adapterOrder;
    TextView order_amount;
    Intent intentSettings;

    ImageView activity_chilyo_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_order);

        rv_orderItem = findViewById(R.id.rv_orderItem);
        order_amount = findViewById(R.id.order_amount);

        getJson();

        elementInit();

        /** kembali ke menu utama **/
        activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityOrder.this, ActivityShoppingCart.class);
                startActivity(intentSettings);
            }
        });

        /** tombol Selesai **/
        Button pay_book_now = findViewById(R.id.complete_button);
        pay_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityOrder.this, activity_chilyo_rating.class);
                startActivity(intentSettings);
            }
        });
    }

    private void elementInit() {
    }

    private void getJson(){
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<OrderItem>> call = service.getOrderItem(SharedPreferencesStore.getAuthUserId(getBaseContext()));
        call.enqueue(new Callback<List<OrderItem>>() {
            @Override
            public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                List<OrderItem> data = response.body();
                int i = 0;
                int size = data.size();
                int total = 0;

                for(i = 0; i < size; i++){
                    total += Integer.parseInt(data.get(i).getProduct_price());
                }

                String total_ = Integer.toString(total);
                order_amount.setText(Support.rupiahFormat(total_) + " K");

                Log.d("GetData", total_);
                generateDataList(response.body());
                //progress.getDialog().dismiss();
            }
            @Override
            public void onFailure(Call<List<OrderItem>> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<OrderItem> orderitem) {
        adapterOrder = new OrderGetItemAdapter(this, orderitem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityOrder.this);
        rv_orderItem.setLayoutManager(layoutManager);
        rv_orderItem.setAdapter(adapterOrder);
    }
}
