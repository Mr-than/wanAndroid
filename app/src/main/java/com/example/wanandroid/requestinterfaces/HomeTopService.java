package com.example.wanandroid.requestinterfaces;

import com.example.wanandroid.retrofit.GET;

public interface HomeTopService {
    @GET(port = "/article/top/json")
    String getTopArticle();


}
