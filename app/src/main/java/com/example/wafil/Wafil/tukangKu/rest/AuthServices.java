package com.example.wafil.Wafil.tukangKu.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthServices {
    private static Retrofit retrofit = null;
    private static final String baseUrl = "http://carexports.uk/wan_api/v1/userHandler/";
//    private static final String baseUrl = "https://anggatukangku.000webhostapp.com/api/";

    public static RestInterface getService() {
        if(retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .cache(null)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(RestInterface.class);
        }

        else return retrofit.create(RestInterface.class);
    }
}
