package com.example.wanandroid.ui.webpage

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ActivityWebBinding


class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebBinding.inflate(layoutInflater)
        val url = intent.getStringExtra("url")
        Log.d("88888888888888888", "onCreate:----------------------$url")

        binding.webView.run {
            settings.javaScriptEnabled = true

            webViewClient = object:WebViewClient(){
                @SuppressLint("WebViewClientOnReceivedSslError")
                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    handler!!.proceed()
                }
            }

            settings.loadWithOverviewMode = true;
            settings.domStorageEnabled = true;
            settings.blockNetworkImage = false;
            settings.useWideViewPort = true;
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
            binding.webView.loadUrl(url!!)

        }


    }
}