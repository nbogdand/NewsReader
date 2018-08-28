package com.example.bogdan.newsapp.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IconBetterIdeaClient {

    public static Retrofit retrofit = null;
    public static Retrofit getClient(){

        if( retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(" https://icons.better-idea.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
