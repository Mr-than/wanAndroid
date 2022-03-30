package com.example.wanandroid.repository


import android.util.Log
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.databean.originaldata.Banner
import com.example.wanandroid.databean.originaldata.CommendArticle
import com.example.wanandroid.databean.originaldata.TopArticle
import com.example.wanandroid.requestinterfaces.*
import com.example.wanandroid.retrofit.MyRetrofit
import com.example.wanandroid.ui.adapter.HomeAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.util.ArrayList

object HomeRepository {

        private val job= Job()
        private val scope= CoroutineScope(job)

        private val myRetrofit:MyRetrofit= MyRetrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .builder()

    fun getHomeData(hasData:Boolean,p:Int,observer: Observer<List<Article>>){

            Observable.create(ObservableOnSubscribe<List<String>> { t ->

                scope.launch {
                        val a = async {

                                val homeTopService: HomeTopService
                                var data: String? = null
                                try {
                                    homeTopService = myRetrofit.create(HomeTopService::class.java)
                                    data = homeTopService.topArticle
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                data!!

                        }


                    val b=async {
                        val homeCommendService:HomeCommendService
                        var data:String?=null

                        try {
                            homeCommendService= myRetrofit.create(HomeCommendService::class.java)
                            data=homeCommendService.getCommendData("$p")
                        }catch (e:Exception){
                            e.printStackTrace()
                        }
                        data
                    }

                    val data=ArrayList<String>(2)

                    if(!hasData) {
                        data.add(a.await().toString())
                    }
                    data.add(b.await().toString())
                    t.onNext(data)
                }
        })
                .map { data->
                    val l=ArrayList<Article>()

                    val gson=Gson()
                    var topArticle:TopArticle?=null
                    if(!hasData) {
                        topArticle = gson.fromJson(data[0], object : TypeToken<TopArticle>() {}.type)
                    }


                    val commendArticle:CommendArticle=gson.fromJson(if (data.size>1){data[1]}else{data[0]},object:TypeToken<CommendArticle>(){}.type)


                    try {
                        if (!hasData){
                            for (i in 0 until topArticle!!.data.size) {

                                val topType: Int = if (i == 0) {
                                    HomeAdapter.firstTopItem
                                } else if (topArticle.data[i].fresh && topArticle.data[i].tags.isNotEmpty()) {
                                    HomeAdapter.treeColorsItem
                                } else if (topArticle.data[i].fresh && topArticle.data[i].tags.isEmpty()) {
                                    HomeAdapter.firstTopItem
                                } else if (topArticle.data[i].tags.isNotEmpty()) {
                                    HomeAdapter.redAndBlueItem
                                } else {
                                    HomeAdapter.justRedItem
                                }



                                l.add(
                                    Article(
                                        topType,
                                        topArticle.data[i].link,
                                        topArticle.data[i].envelopePic,
                                        topArticle.data[i].author.ifEmpty { topArticle.data[i].shareUser },
                                        topArticle.data[i].niceShareDate,
                                        topArticle.data[i].chapterName,
                                        topArticle.data[i].superChapterName,
                                        if (topArticle.data[i].tags.isNotEmpty()) {
                                            topArticle.data[i].tags[0].name
                                        } else {
                                            null
                                        },
                                        topArticle.data[i].title,
                                        topArticle.data[i].fresh,
                                        true
                                    )
                                )

                            }
                    }

                        for (i in 0 until commendArticle.data.datas.size) {

                            val commendType =
                                if (commendArticle.data.datas[i].fresh && commendArticle.data.datas[i].tags.isNotEmpty()) {
                                    HomeAdapter.redAndBlueItem
                                } else if (commendArticle.data.datas[i].tags.isNotEmpty()&&commendArticle.data.datas[i].envelopePic.isEmpty()) {
                                    HomeAdapter.justBlueItem
                                } else if (commendArticle.data.datas[i].envelopePic.isNotEmpty()) {
                                    HomeAdapter.photoItem
                                } else {
                                    HomeAdapter.commendItem
                                }

                            l.add(
                                Article(
                                    commendType,
                                    commendArticle.data.datas[i].link,
                                    commendArticle.data.datas[i].envelopePic,
                                    commendArticle.data.datas[i].author.ifEmpty { commendArticle.data.datas[i].shareUser },
                                    commendArticle.data.datas[i].niceShareDate,
                                    commendArticle.data.datas[i].chapterName,
                                    commendArticle.data.datas[i].superChapterName,
                                    if (commendArticle.data.datas[i].tags.isNotEmpty()) {
                                        commendArticle.data.datas[i].tags[0].name
                                    } else {
                                        null
                                    },
                                    commendArticle.data.datas[i].title,
                                    commendArticle.data.datas[i].fresh,
                                    false
                                )
                            )
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                    l
                }
            .subscribeOn(Schedulers.newThread())
            .subscribe(observer)
    }


    fun getBanner()=flow{
        val bannerPhoto:BannerPhoto= myRetrofit.create(BannerPhoto::class.java)
        val banner=bannerPhoto.url
        emit(banner)
    }.map {
        val gson=Gson()
        val banner:Banner=gson.fromJson(it,object :TypeToken<Banner>(){}.type)
        banner
    }.flowOn(Dispatchers.IO)
}