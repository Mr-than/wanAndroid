package com.example.wanandroid.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.R
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.databinding.HomeFragmentBinding
import com.example.wanandroid.model.GamItemTouchCallback
import com.example.wanandroid.ui.adapter.HomeAdapter
import com.example.wanandroid.ui.mainactivity.MainActivity
import com.example.wanandroid.ui.webpage.WebActivity

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: HomeFragmentBinding
    private var pageNumber = 0
    private var num: Int = 0
    private var p: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        initContent()



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }


    private fun initContent() {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //将抽屉布局和toolbar关联
        drawerLayout = requireActivity().findViewById(R.id.drawerlayout)
        (activity as MainActivity).setSupportActionBar(binding.homeToolbar)
        val toggle = ActionBarDrawerToggle(activity, drawerLayout, binding.homeToolbar, 0, 0)
        toggle.syncState()

        val manner = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        binding.homeRv.layoutManager = manner

        val list = ArrayList<Article>()
        val adapter = HomeAdapter(list, requireContext(),viewModel)


        viewModel.bannerPhoto.observe(requireActivity()) {
            adapter.setBanner(it)
            viewModel.articleData(0)
            binding.homeRv.adapter = adapter
        }
        viewModel.setBanner()

        viewModel.articleData.observe(requireActivity()) {
            if (!list.containsAll(it)) {
                list.addAll(it)
                num += it.size
                ++p
                adapter.update(list)
            }
        }
        viewModel.articleData(0)

        binding.homeRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int = manner.findLastVisibleItemPosition()
                    if (position >= num - 3) {
                        viewModel.articleData(p)
                    }
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        //val aaa=ItemTouchHelper(GamItemTouchCallback(dpToPx(requireContext(),160f),adapter))
        //aaa.attachToRecyclerView(binding.homeRv)

        viewModel.urlLiveData.observe(requireActivity()){
            val intent=Intent(requireActivity(),WebActivity::class.java)
            intent.putExtra("url",it)
            startActivity(intent)
        }


    }

    private fun dpToPx(context: Context, value: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        ).toInt()
    }


}