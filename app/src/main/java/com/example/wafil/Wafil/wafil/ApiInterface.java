package com.example.wafil.Wafil.wafil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search/search.php")
    Call<List<Product>> getProduct (
            @Query("key") String keyword
    );

    @GET("search/category_product.php")
    Call<List<Category_Product>> getCategoryProduct (
            @Query("key") String keyword
    );

}
