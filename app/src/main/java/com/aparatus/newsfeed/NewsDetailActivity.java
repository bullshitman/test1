package com.aparatus.newsfeed;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.aparatus.newsfeed.Models.Article;
import com.aparatus.newsfeed.Models.ArticleDetail;
import com.aparatus.newsfeed.Retrofit.ApiService;
import com.aparatus.newsfeed.Retrofit.RetrofitApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PATH = "tempLink";
    private String articleLink;
    private String tempString;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            articleLink = extras.getString(EXTRA_PATH);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
//        articleLink = "/2018/02/15/lester-sheffild-junajted-prognoz-na-kubok-anglii-16-02-2018";
        RetrofitApi apiService = ApiService.getListEventService();
        apiService.getArticleDetails(articleLink).enqueue(new Callback<ArticleDetail>() {
            @Override
            public void onResponse(Call<ArticleDetail> call, Response<ArticleDetail> response) {
                if (response.isSuccessful()){
                    Log.d("Uploading post","done with link " + articleLink);
                    StringBuilder articleDesc = new StringBuilder();
                    List<Article> article = response.body().getArticle();
                    for (Article element : article) {
                        articleDesc.append(element.getHeader()).append('\n');
                        articleDesc.append(element.getText()).append('\n');
                    }

                    TextView team1 = findViewById(R.id.team1);
                    team1.setText(response.body().getTeam1());

                    TextView team2 = findViewById(R.id.team2);
                    team2.setText(response.body().getTeam2());

                    tempString = getResources().getText(R.string.when) + " " + response.body().getTime();
                    TextView time = findViewById(R.id.time);
                    time.setText(tempString);

                    tempString = getResources().getText(R.string.tournament) + " " + response.body().getTournament();
                    TextView tournament = findViewById(R.id.tournament);
                    tournament.setText(tempString);

                    tempString = getResources().getText(R.string.place) + " " + response.body().getPlace();
                    TextView place = findViewById(R.id.place);
                    place.setText(tempString);

                    tempString = getResources().getText(R.string.prediction) + " " + response.body().getPrediction();
                    TextView prediction = findViewById(R.id.prediction);
                    prediction.setText(tempString);

                    TextView articleDescription = findViewById(R.id.description);
                    articleDescription.setText(articleDesc);
            }
        }

            @Override
            public void onFailure(Call<ArticleDetail> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(NewsDetailActivity.this, R.string.no_connection, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(NewsDetailActivity.this, R.string.error_news, Toast.LENGTH_SHORT).show();
                }
                Log.d("Uploading post","failure " + t);
            }
        });
    }
}
