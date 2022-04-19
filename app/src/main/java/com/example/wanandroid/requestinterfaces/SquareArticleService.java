package com.example.wanandroid.requestinterfaces;

import com.example.wanandroid.retrofit.GET;
import com.example.wanandroid.retrofit.PATH;

public interface SquareArticleService {
    @GET(port = "/user_article/list/{p}/json")
    @PATH(value = "p")
    public String getArticle(String p);
}
