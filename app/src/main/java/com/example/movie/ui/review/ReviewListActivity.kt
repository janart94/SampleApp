package com.example.movie.ui.review

import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.databinding.ActivityReviewListBinding
import com.example.movie.ui.adapter.ReviewerListAdapter
import com.example.movie.utils.Utils
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ReviewListActivity : BindingActivity<ActivityReviewListBinding>(R.layout.activity_review_list) {

    @Inject
    lateinit var listViewModelFactory: ReviewListViewModel.AssistedFactory


    val viewModel: ReviewListViewModel by viewModels {
        ReviewListViewModel.provideFactory(listViewModelFactory, movieId)
    }

    private val movieId: String by bundleNonNull(Utils.EXTRA_MOVIE_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@ReviewListActivity
            reviewerAdapter = ReviewerListAdapter()
            vm = viewModel
        }
    }
}
