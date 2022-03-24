package com.example.wanandroid.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.databean.originaldata.CommendArticle
import com.example.wanandroid.databean.originaldata.TopArticle
import com.example.wanandroid.repository.HomeRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val articleData=MutableLiveData<List<Article>>()
    val _articleData=articleData


    fun articleData(){
        HomeRepository.getHomeData(object :Observer<List<Article>>{
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: List<Article>) {
                articleData.postValue(t)
            }
            override fun onError(e: Throwable) {
            }
            override fun onComplete() {
            }
        })

    }

}