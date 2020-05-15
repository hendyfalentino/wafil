package com.example.wafil.Wafil.tukangKu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.tukangKu.rest.RestInterface;
import com.example.wafil.Wafil.tukangKu.rest.RestServices;
import com.example.wafil.Wafil.tukangKu.rest.request.OrderRequest;
import com.example.wafil.Wafil.tukangKu.rest.response.BaseResponse;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private TextView mTvName, mTvAddress, mTvWorkerAddress, mTvStatus;
    private Button mBtnOrder, mBtnCancel, mBtnRating;
    private Timer mTimer = new Timer();
    private SharedPreferences mPref;

    private RestInterface mServices;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        mPref = getSharedPreferences("pref", MODE_PRIVATE);
        mServices = RestServices.getService();

        mTvWorkerAddress = findViewById(R.id.tv_address);
        mTvAddress = findViewById(R.id.tv_work_address);
        mTvName = findViewById(R.id.tv_name);
        mTvStatus = findViewById(R.id.tv_status);
        mBtnCancel = findViewById(R.id.btn_cancel_order);
        mBtnOrder = findViewById(R.id.btn_order);
        mBtnRating = findViewById(R.id.btn_rating);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                Call<BaseResponse> cancel = mServices.cancelOrder(
                        new OrderRequest()
                                .setTukangId(getIntent().getIntExtra("id", 0))
                                .setUserId(mPref.getInt("user_id", -1))
                                .setWorktype(getIntent().getIntExtra("worktype", 0))
                                .setWorkAddress(getIntent().getStringExtra("workaddress")));

                cancel.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if(response.body().isSuccess()) {
                            setDefaultButtonState();
                            Toast.makeText(OrderActivity.this, "Pesanan dibatalkan", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(OrderActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                        }

                        checkWorkerConfirmation();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(OrderActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mBtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
                Call<BaseResponse> order = mServices.addOrder(
                        new OrderRequest()
                            .setTukangId(getIntent().getIntExtra("id", 0))
                            .setUserId(mPref.getInt("user_id", -1))
                            .setWorktype(getIntent().getIntExtra("worktype", 0))
                            .setWorkAddress(getIntent().getStringExtra("workaddress")));

                order.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if(response.body().isSuccess()) {
                            setDisableButtonState();
                            Toast.makeText(OrderActivity.this, "Pesanan berhasil, menunggu persetujuan tukang", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(OrderActivity.this, "Koneksi Internet Bermasalahs", Toast.LENGTH_SHORT).show();
                        }

                        checkWorkerConfirmation();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(OrderActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        mTvName.setText("Nama :"  + getIntent().getStringExtra("name"));
        mTvAddress.setText("Alamat Anda: " + getIntent().getStringExtra("workaddress"));
        mTvWorkerAddress.setText("Alamat Mitra : " + getIntent().getStringExtra("address"));

        checkWorkerConfirmation();
    }

    private void checkWorkerConfirmation() {
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkWorkOrder();
            }
        }, 0, 10 * 1000);
    }

    private void checkWorkOrder() {
        Call<BaseResponse> checkWorkderConfirmation = mServices.checkWorkerConfirmation(
                new OrderRequest()
                    .setTukangId(getIntent().getIntExtra("id", 0))
                    .setWorktype(getIntent().getIntExtra("worktype", 0))
                    .setUserId(mPref.getInt("user_id", -1)));

        checkWorkderConfirmation.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                switch (response.body().getCode()) {
                    // Tidak ada order
                    case 0 :
                        setDefaultButtonState();
                        mTvStatus.setText("Status : Belum Order");
                        break;

                    // Order belum dikonfirmasi
                    case 1 :
                        setDisableButtonState();
                        mTvStatus.setText("Status : Menunggu persetujuan mitra");
                        break;

                    // Order sudah dikonfirmasi
                    case 2 :
                        setCancelButtonState();
                        mTvStatus.setText("Status : Mitra menyetujui pesanan Anda");
//                        Toast.makeText(OrderActivity.this, "Pesanan sudah disetujui tukang.", Toast.LENGTH_LONG).show();
                        break;

                    case 98 :
                        setDefaultButtonState();
                        mTvStatus.setText("Status : Mitra membatalkan pesanan Anda");
//                        Toast.makeText(OrderActivity.this, "Pesanan dibatalkan", Toast.LENGTH_LONG).show();
                        break;

                    case 99 :
                        mTvStatus.setText("Status : Anda pernah melakukan pesanan ini, silahkan lakukan rating");
                        setRatingButtonState();
                        break;

                    default :
                        mTvStatus.setText("Status : -");
                        setDefaultButtonState();
                        break;
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    private void setDisableButtonState() {
        mBtnOrder.setEnabled(false);
        mBtnOrder.setText("Menunggu Persetujuan");
        mBtnOrder.setBackgroundColor(getResources().getColor(R.color.gray));

        mBtnOrder.setVisibility(View.VISIBLE);
        mBtnCancel.setVisibility(View.GONE);
    }

    private void setDefaultButtonState() {
        mBtnOrder.setEnabled(true);
        mBtnOrder.setText("Pesan Tukang Ini");
        mBtnOrder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mBtnOrder.setVisibility(View.VISIBLE);
        mBtnCancel.setVisibility(View.GONE);
        mBtnRating.setVisibility(View.GONE);
    }

    private void setCancelButtonState() {
        mBtnOrder.setVisibility(View.GONE);
        mBtnCancel.setVisibility(View.VISIBLE);
        mBtnRating.setVisibility(View.GONE);
    }

    private void setRatingButtonState() {

        mBtnOrder.setVisibility(View.VISIBLE);
        mBtnCancel.setVisibility(View.GONE);
        mBtnRating.setVisibility(View.VISIBLE);
        mBtnOrder.setEnabled(true);
        mBtnOrder.setText("Pesan Tukang Ini");
        mBtnOrder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        mBtnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, RatingActivity.class);
                intent.putExtra("tukangid", getIntent().getIntExtra("id", 0));
                intent.putExtra("userid", mPref.getInt("user_id", -1));

                startActivity(intent);
            }
        });
    }
}
