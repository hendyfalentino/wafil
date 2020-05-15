package com.example.wafil.Wafil.tukangKu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wafil.R;
import com.example.wafil.Wafil.LoginActivity;
import com.example.wafil.Wafil.tukangKu.rest.RestInterface;
import com.example.wafil.Wafil.tukangKu.rest.RestServices;
import com.example.wafil.Wafil.tukangKu.rest.request.OrderRequest;
import com.example.wafil.Wafil.tukangKu.rest.response.GetLastOrderResponse;
import com.example.wafil.Wafil.tukangKu.utilities.SessionManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TukangKuMainActivity extends AppCompatActivity {

    private EditText mEtInputAddress;
    private AppCompatSpinner mSpWorks;
    private Button mBtnSearch, mBtnCheckOrder;
    private int mSelectedWorktype = 1;
    private String mAddress;

    private List<String> mWorksList = new ArrayList<>();
    private RestInterface mService;

    SessionManager sessionManager;
    private SharedPreferences mPref;
    private Button mBtnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionManager = new SessionManager(this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("MainActivity", msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        setContentView(R.layout.activity_main);
        mService = RestServices.getService();
        mPref = getSharedPreferences("pref", MODE_PRIVATE);

        mWorksList.add("Bangunan");
        mWorksList.add("Listrik");
        mWorksList.add("Pipa Ledeng");

        mEtInputAddress = findViewById(R.id.et_input_address);
        mSpWorks = findViewById(R.id.sp_work);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnCheckOrder = findViewById(R.id.btn_check_order);
        mBtnLogout = findViewById(R.id.btn_logout);

        mEtInputAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mAddress = editable.toString();
            }
        });

        mSpWorks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectedWorktype = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager sessionManager = new SessionManager(TukangKuMainActivity.this);
                sessionManager.LogOut();

                Intent intent = new Intent(TukangKuMainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mSpWorks.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mWorksList));

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TukangKuMainActivity.this, TukangListActivity.class);
                intent.putExtra("alamat", mAddress);
                intent.putExtra("worktype", mSelectedWorktype);
                startActivity(intent);
            }
        });

        mBtnCheckOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetLastOrderResponse> getLastOrder = mService.getLastOrder(
                        new OrderRequest()
                                .setUserId(mPref.getInt("user_id", -1)));

                getLastOrder.enqueue(new Callback<GetLastOrderResponse>() {
                    @Override
                    public void onResponse(Call<GetLastOrderResponse> call, Response<GetLastOrderResponse> response) {
                        if(response.body().isSuccess()) {
                            Intent intent = new Intent(TukangKuMainActivity.this, OrderActivity.class);
                            intent.putExtra("id", response.body().getOrder().getTukangId());
                            intent.putExtra("workaddress", response.body().getOrder().getWorkaddress());
                            intent.putExtra("worktype", response.body().getOrder().getWorktype());
                            intent.putExtra("name", response.body().getOrder().getTukangName());
                            intent.putExtra("address", response.body().getOrder().getAddress());
                            startActivity(intent);

                        } else {
                            Toast.makeText(TukangKuMainActivity.this, "Tidak ada order", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetLastOrderResponse> call, Throwable t) {
                        Toast.makeText(TukangKuMainActivity.this, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
