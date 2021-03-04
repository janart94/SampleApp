package com.example.movie.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.ui.adapter.MovieListAdapter
import com.example.movie.ui.adapter.ViewPagerAdapter
import com.skydoves.bindables.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {


    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@MainActivity
            movieAdapter = MovieListAdapter()
            postAdapter = ViewPagerAdapter()
            vm = viewModel
        }
    }
}
