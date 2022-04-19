package com.example.wanandroid.ui.square


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.repository.SquareRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable


class SquareViewModel : ViewModel() {

    private val articleMuLiveData= MutableLiveData<List<Article>>()
    val articleLiveData:LiveData<List<Article>> =articleMuLiveData

    fun getSquareData(p:Int){

        SquareRepository.getSquareArticle(p,object: Observer<List<Article>>{

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: List<Article>) {
                articleMuLiveData.postValue(t)
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }

        })

    }

}