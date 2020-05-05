package com.example.wafil.Wafil.wafil;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wafil.R;
import com.example.wafil.Wafil.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductActivity extends AppCompatActivity {

    SessionManager sessionManager;
    private RecyclerView recyclerView;
    private GridLayout gridLayout;
    private List<Category_Product> category_product;
    private AdapterCategoryProduct adapterCategoryProduct;
    private ApiInterface apiInterface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product);

        recyclerView = findViewById(R.id.category_product_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterCategoryProduct);
        fetchCategoryProduct("");
        sessionManager = new SessionManager(this);
        sessionManager.checkLogIn();

    }

    public void fetchCategoryProduct (String key){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Category_Product>> call = apiInterface.getCategoryProduct(key);

        call.enqueue(new Callback<List<Category_Product>>() {
            @Override
            public void onResponse(Call<List<Category_Product>> call, Response<List<Category_Product>> response) {

                category_product = response.body();
                recyclerView.setLayoutManager(new GridLayoutManager(CategoryProductActivity.this, 2));
                AdapterCategoryProduct adapterCategoryProduct = new AdapterCategoryProduct(category_product, CategoryProductActivity.this);
                recyclerView.setAdapter(adapterCategoryProduct);
            }

            @Override
            public void onFailure(Call<List<Category_Product>> call, Throwable t) {
                Toast.makeText(CategoryProductActivity.this, "Error on :" + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
