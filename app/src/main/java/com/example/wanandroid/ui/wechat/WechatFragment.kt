package com.example.wanandroid.ui.wechat

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wanandroid.ui.mainactivity.MainActivity
import com.example.wanandroid.R
import com.example.wanandroid.databinding.WechatFragmentBinding

class WechatFragment : Fragment() {

    private lateinit var viewModel: WechatViewModel
    private lateinit var binding:WechatFragmentBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= WechatFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        initContent()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.wechat_menu,menu)
    }

    private fun initContent(){
        drawerLayout=requireActivity().findViewById(R.id.drawerlayout)
        (activity as MainActivity).setSupportActionBar(binding.wechatToolbar)
        val toggle= ActionBarDrawerToggle(activity,drawerLayout,binding.wechatToolbar,0,0)
        toggle.syncState()
    }

}