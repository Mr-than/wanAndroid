package com.example.wanandroid.ui.wechat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wanandroid.R

class WechatFragment : Fragment() {

    companion object {
        fun newInstance() = WechatFragment()
    }

    private lateinit var viewModel: WechatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wechat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WechatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}