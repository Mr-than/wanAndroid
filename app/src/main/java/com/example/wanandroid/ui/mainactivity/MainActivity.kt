package com.example.wanandroid.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import io.reactivex.rxjava3.core.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wanandroid.R
import com.example.wanandroid.repository.HomeRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private lateinit var addDrawerListener: DrawerLayout
    private lateinit var toolbar:Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

         addDrawerListener= findViewById(R.id.drawerlayout)
         bottomNavigationView=findViewById(R.id.main_activity_bv)


            val m=ViewModelProvider(this).get(MainActivityViewModel::class.java)


            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

            val navController = navHostFragment.navController

            findViewById<BottomNavigationView>(R.id.main_activity_bv).setupWithNavController(navController)


        }
    }