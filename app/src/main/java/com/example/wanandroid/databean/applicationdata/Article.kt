package com.example.wanandroid.databean.applicationdata

data class Article(
    private val type:Int,
    private val articleName:String,
    private val auName:String,
    private val shareData:String,
    private val chapterName:String,
    private val superChapterName:String,
    private val title:String,
    private val fresh:Boolean,
    private val top:Boolean) {

    fun getArticleName()=articleName
    fun getType()=type
    fun getAuName()=auName
    fun getShareData()=shareData
    fun getChapterName()=chapterName
    fun getSuperChapterName()=superChapterName
    fun getTitle()=title
    fun getFresh()=fresh
    fun getTop()=top

}