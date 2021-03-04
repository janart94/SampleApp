package com.example.movie.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.databinding.ActivityDetailBinding
import com.example.movie.model.Movie
import com.example.movie.ui.adapter.CompanyAdapter
import com.example.movie.ui.adapter.SimilarMoviesAdapter
import com.example.movie.utils.Utils
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : BindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    @Inject
    lateinit var detailViewModelFactory: DetailViewModel.AssistedFactory


    val viewModel: DetailViewModel by viewModels {
        DetailViewModel.provideFactory(detailViewModelFactory, movieData.id)
    }

    private val movieData: Movie by bundleNonNull(Utils.EXTRA_MOVIE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@DetailActivity
            movie = movieData
            companyAdapter = CompanyAdapter()
            movieAdapter = SimilarMoviesAdapter()
            vm = viewModel
        }
    }
}
