package com.example.wanandroid.repository

import MyRetrofit
import android.util.Log
import com.example.wanandroid.requestinterfaces.HomeTopData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers

object HomeRepository {

        private val myRetrofit:MyRetrofit=MyRetrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .builder()

    fun getHomeData(observer: Observer<String>){

            val homeObservable=Observable.create(ObservableOnSubscribe<String> { t ->

            val homeTopData:HomeTopData= myRetrofit.create(HomeTopData::class.java)
            val a=homeTopData.getTopArticle()


            t.onNext(a)
        })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }


}