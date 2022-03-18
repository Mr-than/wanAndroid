package com.example.wanandroid.ui.square

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wanandroid.ui.mainactivity.MainActivity
import com.example.wanandroid.R
import com.example.wanandroid.databinding.SquareFragmentBinding

class SquareFragment : Fragment() {

    private lateinit var viewModel: SquareViewModel
    private lateinit var binding:SquareFragmentBinding
    private lateinit var drawerLayout: DrawerLayout

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
        drawerLayout=requireActivity().findViewById(R.id.drawerlayout)
        (activity as MainActivity).setSupportActionBar(binding.squareToolbar)
        val toggle= ActionBarDrawerToggle(activity,drawerLayout,binding.squareToolbar,0,0)
        toggle.syncState()
    }


}