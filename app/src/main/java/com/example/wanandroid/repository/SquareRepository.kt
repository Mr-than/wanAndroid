package com.example.wanandroid.repository


import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.databean.originaldata.SquareArticle
import com.example.wanandroid.requestinterfaces.SquareArticleService
import com.example.wanandroid.retrofit.MyRetrofit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers

object SquareRepository {

    private val myRetrofit: MyRetrofit = MyRetrofit.Builder()
        .baseUrl("https://www.wanandroid.com")
        .builder()

    fun getSquareArticle(page:Int, observer:Observer<List<Article>>){

        val squareArticleService:SquareArticleService= myRetrofit.create(SquareArticleService::class.java)


        val gson= Gson()


        Observable.create(ObservableOnSubscribe<String>{
            var data:String?=null
            try {
                data=squareArticleService.getArticle("$page")

            }catch (e:Exception){
                e.printStackTrace()
            }
            it.onNext(data!!)
        })

            .map {
                val squareArticle:SquareArticle=gson.fromJson(it,object:TypeToken<SquareArticle>(){}.type)
                val l= ArrayList<Article>()

                if (squareArticle.data.datas.isNotEmpty()){
                    for (i in 0 until squareArticle.data.datas.size){
                        l.add(
                            Article(
                                if(squareArticle.data.datas[i].fresh){0}else{1},
                                squareArticle.data.datas[i].link,
                                squareArticle.data.datas[i].envelopePic,
                                squareArticle.data.datas[i].author.ifEmpty { squareArticle.data.datas[i].shareUser },
                                squareArticle.data.datas[i].niceShareDate,
                                squareArticle.data.datas[i].chapterName,
                                squareArticle.data.datas[i].superChapterName,
                                null,
                                squareArticle.data.datas[i].title,
                                squareArticle.data.datas[i].fresh,
                                false
                            )
                        )

                    }
                }
           l
        } .subscribeOn(Schedulers.newThread())
            .subscribe(observer)
    }
}