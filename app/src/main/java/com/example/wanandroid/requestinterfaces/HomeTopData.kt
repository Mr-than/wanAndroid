package com.example.wanandroid.requestinterfaces

import GET

interface HomeTopData {
    @GET(port = "/article/top/json")
    fun getTopArticle():String



}