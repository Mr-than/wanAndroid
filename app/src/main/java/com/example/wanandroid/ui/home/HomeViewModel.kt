package com.example.wanandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.repository.HomeRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val articleMuLiveData=MutableLiveData<Article>()
    private val articleLiveData=articleMuLiveData


    fun articleData(){

        HomeRepository.getHomeData(object :Observer<String>{
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: String) {




            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        })

    }

}