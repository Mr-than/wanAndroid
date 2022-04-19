package com.example.wanandroid.ui.square

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanandroid.ui.mainactivity.MainActivity
import com.example.wanandroid.R
import com.example.wanandroid.databean.applicationdata.Article
import com.example.wanandroid.databinding.SquareFragmentBinding
import com.example.wanandroid.ui.adapter.SquareAdapter

class SquareFragment : Fragment() {

    private lateinit var viewModel: SquareViewModel
    private lateinit var binding:SquareFragmentBinding
    private lateinit var drawerLayout: DrawerLayout
    private var p=0
    private var num=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= SquareFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)
        initContent()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.square_menu,menu)
    }

    private fun initContent(){
        viewModel=ViewModelProvider(requireActivity()).get(SquareViewModel::class.java)
        drawerLayout=requireActivity().findViewById(R.id.drawerlayout)
        (activity as MainActivity).setSupportActionBar(binding.squareToolbar)
        val toggle= ActionBarDrawerToggle(activity,drawerLayout,binding.squareToolbar,0,0)
        toggle.syncState()

        val list=ArrayList<Article>()
        val manger=LinearLayoutManager(requireActivity())
        val adapter=SquareAdapter(list)
        binding.squareRv.layoutManager=manger
        binding.squareRv.adapter=adapter


        viewModel.articleLiveData.observe(requireActivity()){

            if(!list.containsAll(it)){
                list.addAll(it)
                num+=it.size
                ++p
                adapter.update(list)
            }

        }
        viewModel.getSquareData(0)

        binding.squareRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState== RecyclerView.SCROLL_STATE_IDLE) {
                    val position: Int = manger.findLastVisibleItemPosition()
                    if (position >= num - 3) {
                        viewModel.getSquareData(p)
                    }
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })


    }


}