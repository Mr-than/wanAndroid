package com.example.wanandroid.databean.applicationdata

data class Article(
    private val type:Int,
    private val url:String,
    private val photo:String?,
    private val auName:String,
    private val shareData:String,
    private val chapterName:String,
    private val superChapterName:String,
    private val source:String?,
    private val content:String,
    private val fresh:Boolean,
    private val top:Boolean) {


    fun getType()=type
    fun getAuName()=auName
    fun getShareData()=shareData
    fun getChapterName()=chapterName
    fun getSuperChapterName()=superChapterName
    fun getContent()=content
    fun getSource()=source


}