package com.example.bogdan.newsapp.Interface;

import com.example.bogdan.newsapp.Common.Common;
import com.example.bogdan.newsapp.Model.News;
import com.example.bogdan.newsapp.Model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("v2/top-headlines?country=us&category=business&apiKey=" + Common.API_KEY)
    Call<Website> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
