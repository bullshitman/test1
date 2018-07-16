package com.aparatus.newsfeed.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit(String MAIN_URL){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){

            retrofit = new Retrofit.Builder().
                    baseUrl(MAIN_URL).
                    addConverterFactory(GsonConverterFactory.create(gson)).
                    build();
        }
        return retrofit;
    }
}
