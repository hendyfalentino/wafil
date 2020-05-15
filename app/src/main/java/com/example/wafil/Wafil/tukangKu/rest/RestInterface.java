package com.example.wafil.Wafil.tukangKu.rest;

import com.example.wafil.Wafil.tukangKu.rest.request.GetTukangRequest;
import com.example.wafil.Wafil.tukangKu.rest.request.OrderRequest;
import com.example.wafil.Wafil.tukangKu.rest.request.RatingRequest;
import com.example.wafil.Wafil.tukangKu.rest.request.UpdateTokenRequest;
import com.example.wafil.Wafil.tukangKu.rest.response.BaseResponse;
import com.example.wafil.Wafil.tukangKu.rest.response.GetLastOrderResponse;
import com.example.wafil.Wafil.tukangKu.rest.response.GetTukangResponse;
import com.example.wafil.Wafil.tukangKu.rest.response.NewLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestInterface {

    @POST("get-tukang.php")
    Call<GetTukangResponse> getTukang(@Body GetTukangRequest request);

    @POST("add-order.php")
    Call<BaseResponse> addOrder(@Body OrderRequest request);

    @POST("get-worker-confirmation.php")
    Call<BaseResponse> checkWorkerConfirmation(@Body OrderRequest request);

    @POST("get-last-order.php")
    Call<GetLastOrderResponse> getLastOrder(@Body OrderRequest request);

    @POST("cancel-order.php")
    Call<BaseResponse> cancelOrder(@Body OrderRequest request);

    @POST("add-rating.php")
    Call<BaseResponse> addRating(@Body RatingRequest request);

    @FormUrlEncoded
    @POST("tukangku_sign_in.php")
    Call<NewLoginResponse> login(@Field("user_name") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("signUp.php")
    Call<BaseResponse> register(@Field("user_name") String email,
                                @Field("user_first_name") String fullname,
                                @Field("password") String password);

    @POST("update-token.php")
    Call<BaseResponse> updateToken(@Body UpdateTokenRequest request);

//    @POST("login.php")
//    Call<LoginResponse> login(@Body LoginRequest request);
}
