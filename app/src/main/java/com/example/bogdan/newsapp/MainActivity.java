package com.example.bogdan.newsapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bogdan.newsapp.Adapter.ListNewsAdapter;
import com.example.bogdan.newsapp.Common.Common;
import com.example.bogdan.newsapp.Interface.NewsService;
import com.example.bogdan.newsapp.Model.Website;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListNewsAdapter mAdapter = null;
    android.app.AlertDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init cache
        Paper.init(this);

        mService = Common.getNewsService();

        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });

        listWebsite = (RecyclerView) findViewById(R.id.list_source);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);

        dialog = new SpotsDialog.Builder().setContext(this).build();


        loadWebsiteSource(false);
    }

    public void loadWebsiteSource(boolean isRefreshed) {

        if(!isRefreshed){

            String cache = Paper.book().read("cache");

            if(cache != null && !cache.isEmpty()){

                Website webSite = new Gson().fromJson(cache,Website.class); //Convert cache from Json to Object
                mAdapter = new ListNewsAdapter(webSite.getArticles(),getBaseContext());
                mAdapter.notifyDataSetChanged();
                listWebsite.setAdapter(mAdapter);

            }else{

                dialog.show();
                //Fetch new data

                mService.getSources().enqueue(new Callback<Website>() {
                    @Override
                    public void onResponse(Call<Website> call, Response<Website> response) {

                        if (response.body().getArticles() != null) {
                            mAdapter = new ListNewsAdapter(response.body().getArticles(),getBaseContext());
                        }else{
                            Toast.makeText(MainActivity.this,"There was an error loading news",Toast.LENGTH_SHORT);
                        }
                        mAdapter.notifyDataSetChanged();
                        listWebsite.setAdapter(mAdapter);

                        //Save to cache
                        Paper.book().write("cache",new Gson().toJson(response.body()));

                        // swipeRefreshLayout.setRefreshing(false);

                    }

                    @Override
                    public void onFailure(Call<Website> call, Throwable t) {

                    }
                });

                dialog.dismiss();

            }
        }else {  // Swipe to refresh

            swipeRefreshLayout.setRefreshing(true);

            dialog.show();
            //Fetch new data

            mService.getSources().enqueue(new Callback<Website>() {
                @Override
                public void onResponse(Call<Website> call, Response<Website> response) {
                    mAdapter = new ListNewsAdapter(response.body().getArticles(),getBaseContext());
                    mAdapter.notifyDataSetChanged();
                    listWebsite.setAdapter(mAdapter);

                    //Save to cache
                    Paper.book().write("cache",new Gson().toJson(response.body()));

                    swipeRefreshLayout.setRefreshing(false);

                }

                @Override
                public void onFailure(Call<Website> call, Throwable t) {

                }
            });

            swipeRefreshLayout.setRefreshing(false);

            dialog.dismiss();

        }
    }
}
