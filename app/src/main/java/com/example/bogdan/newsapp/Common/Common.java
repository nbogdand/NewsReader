package com.example.bogdan.newsapp.Common;

import com.example.bogdan.newsapp.Interface.IconBetterIdeaService;
import com.example.bogdan.newsapp.Interface.NewsService;
import com.example.bogdan.newsapp.Remote.IconBetterIdeaClient;
import com.example.bogdan.newsapp.Remote.RetrofitClient;

public class Common {

    private static final String BASE_URL = "https://newsapi.org/";

    public static final String API_KEY ="6b8210f6c41e469b985b668378231c5a";

    public  static NewsService getNewsService(){

        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);

    }

    public static IconBetterIdeaService getIconBetterIdeaService(){

        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }


}
