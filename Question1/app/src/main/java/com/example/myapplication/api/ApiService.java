package com.example.myapplication.api;

import com.example.myapplication.models.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  ApiService {
    //https://openexchangerates.org/api/latest.json?app_id=4262a80a613046068bceb52af8ec813f
    Gson gson=new GsonBuilder().setDateFormat("yyyy-mm-dd HH:mm:ss").create();
    ApiService apiService=new Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/latest.json")
    Call<Currency> ratecurrency(@Query("app_id") String app_id);
}
