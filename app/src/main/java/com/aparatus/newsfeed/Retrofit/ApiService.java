package com.aparatus.newsfeed.Retrofit;

public class ApiService {
    private static final String MAIN_URL = "http://mikonatoruri.win";

    public static RetrofitApi getListEventService(){
        return RetrofitClient.getRetrofit(MAIN_URL).create(RetrofitApi.class);
    }
}
