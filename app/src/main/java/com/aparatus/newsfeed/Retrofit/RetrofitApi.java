package com.aparatus.newsfeed.Retrofit;

import com.aparatus.newsfeed.Models.ArticleDetail;
import com.aparatus.newsfeed.Models.ListEvent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {
        @GET("/list.php")
        Call<ListEvent> getCategoryListEvents(@Query("category") String categoryName);

        @GET("/post.php")
        Call<ArticleDetail> getArticleDetails(@Query("article") String articleLink);
    }

