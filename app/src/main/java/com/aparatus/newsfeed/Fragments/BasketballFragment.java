package com.aparatus.newsfeed.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aparatus.newsfeed.CategoryAdapter;
import com.aparatus.newsfeed.Models.Event;
import com.aparatus.newsfeed.Models.ListEvent;
import com.aparatus.newsfeed.NewsDetailActivity;
import com.aparatus.newsfeed.R;
import com.aparatus.newsfeed.Retrofit.ApiService;
import com.aparatus.newsfeed.Retrofit.RetrofitApi;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketballFragment extends Fragment {
    CategoryAdapter categoryAdapter;
    private RecyclerView rv;
    private RetrofitApi apiService;
    private final String PATH_INC = "basketball";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return rv = (RecyclerView) inflater.inflate(R.layout.fragment_basketball, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
            apiService = ApiService.getListEventService();
            categoryAdapter = new CategoryAdapter(getActivity(), new ArrayList<Event>(0), new CategoryAdapter.Listener() {
                @Override
                public void onClick(String position) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.EXTRA_PATH, position);
                    startActivity(intent);
                    Log.d("Requesting post: ", position);
                }
            });

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(layoutManager);
            rv.setAdapter(categoryAdapter);
            apiService.getCategoryListEvents(PATH_INC).enqueue(new Callback<ListEvent>() {
                @Override
                public void onResponse(Call<ListEvent> call, Response<ListEvent> response) {
                    if (response.isSuccessful()) {
                        categoryAdapter.updateNews(response.body().getEvents());
                        Log.d("Request success", "NEWS DOWNLOADED");
                    }
                }

                @Override
                public void onFailure(Call<ListEvent> call, Throwable t) {
                    if (t instanceof IOException){
                        Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), R.string.error_news, Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
