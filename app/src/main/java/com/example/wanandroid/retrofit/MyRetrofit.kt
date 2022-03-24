package com.example.wanandroid.retrofit


import com.example.wanandroid.retrofit.*
import com.example.wanandroid.retrofit.MakeAnn.t
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.reflect.Proxy.newProxyInstance


@Suppress("UNCHECKED_CAST")
class MyRetrofit(private val u:String){

    fun <T> create(service:Class<T>):T{
        return newProxyInstance(service.classLoader, arrayOf(service)){ _, method, args->

            val arg:String?=if(args==null){
                null
            }else{
                args[0] as? String
            }

            val a=t(u,service.name,method.name,arg)



            startCon(a)
        } as T
    }


    class Builder{
        private lateinit var url:String

        fun baseUrl(baseUrl:String):Builder{
            if(!::url.isInitialized) {
                url = baseUrl
            }
            return this
        }

        fun builder():MyRetrofit{

            if(!::url.isInitialized){
                throw RuntimeException("未初始化baseUrl")
            }

            return MyRetrofit(url)
        }
    }



    private fun startCon(https: MakeAnn.Type):String{


        if(https.value==null){
            https.httpsURLConnection.connect()

            val cookies: Map<String, List<String>> = https.httpsURLConnection.headerFields
            val bu= BufferedReader(InputStreamReader(https.httpsURLConnection.inputStream))

            val setCookies = cookies["Set-Cookie"]!!
            val s = StringBuilder()
            for (key in setCookies) {
                s.append(key).append("; ")
            }

            val cookie:String?=s.substring(0,s.length-1)

            var line:String?

            var sb=StringBuilder()

            while((bu.readLine().also { line = it })!=null){
                sb.append(line)
            }

            val endData:String=if(cookie!=null){
                sb=StringBuilder(sb.substring(0,sb.length-1)).append(",").append("\"Cookie\":").append("\"").append(cookie).append(" \"").append("}")
                sb.toString()
            }else{
                sb.toString()
            }

            return endData
        }else{
            https.httpsURLConnection.connect()

            DataOutputStream(https.httpsURLConnection.outputStream).write(https.value.toByteArray())

            val cookies: Map<String, List<String>> = https.httpsURLConnection.headerFields
            val bu=BufferedReader(InputStreamReader(https.httpsURLConnection.inputStream))

            val setCookies = cookies["Set-Cookie"]!!
            val s = StringBuilder()
            for (key in setCookies) {
                s.append(key).append("; ")
            }
            val cookie:String?=s.substring(0,s.length-1)

            var line:String?

            var sb=StringBuilder()

            while((bu.readLine().also { line = it })!=null){
                sb.append(line)
            }

            val endData:String=if(cookie!=null){
                sb=StringBuilder(sb.substring(0,sb.length-1)).append(",").append("\"Cookie\":").append("\"").append(cookie).append(" \"").append("}")
                sb.toString()
            }else{
                sb.toString()
            }

            return endData
        }
    }
}