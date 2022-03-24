package com.example.wanandroid.requestinterfaces;

import com.example.wanandroid.retrofit.GET;
import com.example.wanandroid.retrofit.PATH;

public interface HomeCommendService {

    @GET(port = "/article/list/{p}/json")
    @PATH(value = "p")
    String getCommendData(String p);

}
