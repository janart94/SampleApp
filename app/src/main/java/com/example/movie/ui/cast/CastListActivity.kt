package com.example.movie.ui.cast

import android.os.Bundle
import androidx.activity.viewModels
import com.example.movie.R
import com.example.movie.databinding.ActivityCastListBinding
import com.example.movie.ui.adapter.CastListAdapter
import com.example.movie.utils.Utils
import com.skydoves.bindables.BindingActivity
import com.skydoves.bundler.bundleNonNull
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CastListActivity : BindingActivity<ActivityCastListBinding>(R.layout.activity_cast_list) {

    @Inject
    lateinit var listViewModelFactory: CastListViewModel.AssistedFactory

    private val viewModel: CastListViewModel by viewModels {
        CastListViewModel.provideFactory(listViewModelFactory, movieId)
    }

    private val movieId: String by bundleNonNull(Utils.EXTRA_MOVIE_ID)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@CastListActivity
            castAdapter = CastListAdapter()
            vm = viewModel
        }
    }
}
