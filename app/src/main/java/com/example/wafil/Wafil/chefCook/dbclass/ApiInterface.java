package com.example.wafil.Wafil.chefCook.dbclass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

//    @FormUrlEncoded
//    @POST("insert.php")
//    Call<DaftarinUrl> saveData {
//        @Field("username") String username;
//        @Field("email") String email;
//        @Field("password") String password;
//    };


    @FormUrlEncoded
    @POST("insert.php")
    Call<DaftarinUrl> saveData(
            @Field("username") String nama,
            @Field("email") String email,
            @Field("pass1") String pass1
    );

}
