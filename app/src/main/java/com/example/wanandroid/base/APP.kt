package com.example.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class APP :Application(){

    override fun onCreate(){
        super.onCreate()
        context=applicationContext
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        private lateinit var context:Context
        fun getApp()=context


    }

}