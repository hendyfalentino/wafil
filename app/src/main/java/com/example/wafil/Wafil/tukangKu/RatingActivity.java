package com.example.wafil.Wafil.tukangKu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.tukangKu.rest.RestInterface;
import com.example.wafil.Wafil.tukangKu.rest.RestServices;
import com.example.wafil.Wafil.tukangKu.rest.request.RatingRequest;
import com.example.wafil.Wafil.tukangKu.rest.response.BaseResponse;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {
    private MaterialRatingBar mMrbRating;
    private EditText mEtReview;
    private Button mBtnSend;
    private RestInterface mService;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mService = RestServices.getService();
        mPref = getSharedPreferences("pref", MODE_PRIVATE);

        mMrbRating = findViewById(R.id.rb_rating);
        mBtnSend = findViewById(R.id.btn_rating);
        mEtReview = findViewById(R.id.et_input_review);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<BaseResponse> addRating = mService.addRating(new RatingRequest()
                    .setTukangId(getIntent().getIntExtra("tukangid", 0))
                    .setUserId(mPref.getInt("user_id", -1))
                    .setRating(Math.round(mMrbRating.getRating()))
                    .setReview(mEtReview.getText().toString())
                );

                addRating.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if(response.body().isSuccess()) {
                            Toast.makeText(RatingActivity.this, "Rating berhasil disimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RatingActivity.this, TukangKuMainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RatingActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {

                        Toast.makeText(RatingActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
