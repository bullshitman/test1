package com.aparatus.newsfeed.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aparatus.newsfeed.Models.Article;
import com.aparatus.newsfeed.Models.ArticleDetail;
import com.aparatus.newsfeed.R;
import com.aparatus.newsfeed.Retrofit.ApiService;
import com.aparatus.newsfeed.Retrofit.RetrofitApi;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class NewsDetailFragment extends Fragment {
    public static final String EXTRA_PATH = "tempLink";
    private String articleLink;
    private String tempString;

    public NewsDetailFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
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

                    TextView team1 = getView().findViewById(R.id.team1);
                    team1.setText(response.body().getTeam1());

                    TextView team2 = getView().findViewById(R.id.team2);
                    team2.setText(response.body().getTeam2());

                    tempString = getResources().getText(R.string.when) + " " + response.body().getTime();
                    TextView time = getView().findViewById(R.id.time);
                    time.setText(tempString);

                    tempString = getResources().getText(R.string.tournament) + " " + response.body().getTournament();
                    TextView tournament = getView().findViewById(R.id.tournament);
                    tournament.setText(tempString);

                    tempString = getResources().getText(R.string.place) + " " + response.body().getPlace();
                    TextView place = getView().findViewById(R.id.place);
                    place.setText(tempString);

                    tempString = getResources().getText(R.string.prediction) + " " + response.body().getPrediction();
                    TextView prediction = getView().findViewById(R.id.prediction);
                    prediction.setText(tempString);

                    TextView articleDescription = getView().findViewById(R.id.description);
                    articleDescription.setText(articleDesc);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetail> call, Throwable t) {
                if (t instanceof IOException){
                    Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), R.string.error_news, Toast.LENGTH_SHORT).show();
                }
                Log.d("Uploading post","failure " + t);
            }
        });
    }

    public void setArticleLink(String path){
        this.articleLink = path;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            articleLink = savedInstanceState.getString("EXTRA_PATH");
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putString("EXTRA_PATH", articleLink);
    }
}
