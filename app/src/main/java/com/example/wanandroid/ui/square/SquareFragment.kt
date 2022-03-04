package com.example.wanandroid.ui.square

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wanandroid.R

class SquareFragment : Fragment() {

    companion object {
        fun newInstance() = SquareFragment()
    }

    private lateinit var viewModel: SquareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.square_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SquareViewModel::class.java)
        // TODO: Use the ViewModel
    }

}