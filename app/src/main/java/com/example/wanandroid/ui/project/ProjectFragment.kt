package com.example.wanandroid.ui.project

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wanandroid.ui.mainactivity.MainActivity
import com.example.wanandroid.R
import com.example.wanandroid.databinding.ProjectFragmentBinding

class ProjectFragment : Fragment() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var viewModel: ProjectViewModel
    private lateinit var binding:ProjectFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding= ProjectFragmentBinding.inflate(inflater)
        setHasOptionsMenu(true)

        initContent()


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.project_menu,menu)
    }

    private fun initContent(){

        drawerLayout=requireActivity().findViewById(R.id.drawerlayout)
        (activity as MainActivity).setSupportActionBar(binding.projectToolbar)
        val toggle=ActionBarDrawerToggle(activity,drawerLayout,binding.projectToolbar,0,0)
        toggle.syncState()


    }


}