package com.example.wafil.Wafil.tukangKu;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.tukangKu.component.TukangItem;
import com.example.wafil.Wafil.tukangKu.component.TukangListAdapter;
import com.example.wafil.Wafil.tukangKu.rest.RestInterface;
import com.example.wafil.Wafil.tukangKu.rest.RestServices;
import com.example.wafil.Wafil.tukangKu.rest.request.GetTukangRequest;
import com.example.wafil.Wafil.tukangKu.rest.response.GetTukangResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TukangListActivity extends AppCompatActivity {
    private TukangListAdapter mAdapter;
    private RecyclerView mRvTukangList;

    private List<TukangItem> mData = new ArrayList<>();
    private RestInterface mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukang_list);

        mService = RestServices.getService();

        mRvTukangList = findViewById(R.id.rv_tukang_list);

        mAdapter = new TukangListAdapter(mData, getIntent().getStringExtra("alamat"));
        mRvTukangList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvTukangList.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        getAllTukang();
    }

    private void getAllTukang() {
        Call<GetTukangResponse> getTukang = mService.getTukang(
                new GetTukangRequest().setWorktype(
                        getIntent().getIntExtra("worktype", -1)));

        getTukang.enqueue(new Callback<GetTukangResponse>() {
            @Override
            public void onResponse(Call<GetTukangResponse> call, Response<GetTukangResponse> response) {
                if(response.body().isSuccess()) {
                    mData.clear();
                    mData.addAll(response.body().getData());

                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(TukangListActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTukangResponse> call, Throwable t) {
                Toast.makeText(TukangListActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
