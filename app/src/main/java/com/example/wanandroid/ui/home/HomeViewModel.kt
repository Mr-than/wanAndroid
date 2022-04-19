package com.example.wanandroid.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.repository.HomeRepository
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val articleMuData=MutableLiveData<List<Article>>()
    val articleData:LiveData<List<Article>> =articleMuData

    private val bannerMuPhoto=MutableLiveData<List<String>>()
    val bannerPhoto=bannerMuPhoto

    private val urlMuLivedata=MutableLiveData<String>()
    val urlLiveData:LiveData<String> =urlMuLivedata

    private var hasData=false


    fun articleData(p:Int){
        HomeRepository.getHomeData(hasData,p,object :Observer<List<Article>>{
            override fun onSubscribe(d: Disposable) {
            }
            override fun onNext(t: List<Article>) {
                articleMuData.postValue(t)
            }
            override fun onError(e: Throwable) {
            }
            override fun onComplete() {
            }

        })
        hasData=true
    }

    fun setBanner(){
        val job= Job()
        val s= CoroutineScope(job)
        s.launch {
            HomeRepository.getBanner().collect{
                val p=ArrayList<String>()
                if(it.data.isNotEmpty())
                for(a in 0 until it.data.size){
                    p.add(it.data[a].imagePath)
                }
                bannerPhoto.postValue(p)
            }
        }
    }

    fun setUrl(url:String){
        urlMuLivedata.value=url
    }

}