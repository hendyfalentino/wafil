package com.example.wafil.Wafil.chilyoHouze.Payment;

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
import com.example.wafil.Wafil.chilyoHouze.Adapters.PaymentGetItemAdapter;
import com.example.wafil.Wafil.chilyoHouze.Model.PaymentItem;
import com.example.wafil.Wafil.chilyoHouze.Order.ActivityOrder;
import com.example.wafil.Wafil.chilyoHouze.ShoppingCart.ActivityShoppingCart;
import com.example.wafil.Wafil.chilyoHouze.Support.Support;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_order;
import com.example.wafil.Wafil.chilyoHouze.activity_chilyo_topup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPayment extends AppCompatActivity {

    RecyclerView rv_paymentItem;
    PaymentGetItemAdapter adapterPayment;
    TextView order_amount;
    Intent intentSettings;

    ImageView activity_chilyo_back;
    ImageView topUp_pay;
    TextView topUp_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_payment);

        rv_paymentItem = findViewById(R.id.rv_paymentItem);
        order_amount = findViewById(R.id.order_amount);

        getJson();

        elementInit();

        /** kembali ke menu utama **/
        activity_chilyo_back = findViewById(R.id.activity_chilyo_back);
        activity_chilyo_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityPayment.this, ActivityShoppingCart.class);
                startActivity(intentSettings);
            }
        });

        /** tombol Bayar **/
        Button pay_book_now = findViewById(R.id.pay_book_now);
        pay_book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityPayment.this, ActivityOrder.class);
                startActivity(intentSettings);
            }
        });

        /** tombol topup**/
        topUp_pay = findViewById(R.id.topUp_pay);
        topUp_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityPayment.this, activity_chilyo_topup.class);
                startActivity(intentSettings);
            }
        });

        topUp_txt = findViewById(R.id.topUp_txt);
        topUp_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSettings = new Intent(ActivityPayment.this, activity_chilyo_topup.class);
                startActivity(intentSettings);
            }
        });
    }

    private void elementInit() {
    }

    private void getJson(){
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<PaymentItem>> call = service.getPaymentItem(SharedPreferencesStore.getAuthUserId(getBaseContext()));
        call.enqueue(new Callback<List<PaymentItem>>() {
            @Override
            public void onResponse(Call<List<PaymentItem>> call, Response<List<PaymentItem>> response) {
                List<PaymentItem> data = response.body();
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
            public void onFailure(Call<List<PaymentItem>> call, Throwable t) {
                Log.d("GetData", t.toString());
            }
        });
    }

    private void generateDataList(List<PaymentItem> paymentitem) {
        adapterPayment = new PaymentGetItemAdapter(this, paymentitem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityPayment.this);
        rv_paymentItem.setLayoutManager(layoutManager);
        rv_paymentItem.setAdapter(adapterPayment);
    }
}
