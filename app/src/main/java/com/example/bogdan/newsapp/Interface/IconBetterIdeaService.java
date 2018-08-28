package com.example.bogdan.newsapp.Interface;

import com.example.bogdan.newsapp.Model.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IconBetterIdeaService {

    @GET()
    Call<IconBetterIdea> getIconUrl(@Url String url);

}
