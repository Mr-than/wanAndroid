package com.example.wanandroid.requestinterfaces;

import com.example.wanandroid.retrofit.GET;

public interface BannerPhoto {
    @GET(port = "/banner/json")
    String getUrl();


}
