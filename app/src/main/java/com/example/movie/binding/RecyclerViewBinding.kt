package com.example.movie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.Cast
import com.example.movie.model.Company
import com.example.movie.model.Movie
import com.example.movie.model.Review
import com.example.movie.ui.adapter.*
import com.example.movie.ui.main.MainViewModel
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("paginationMovieList")
    fun paginationMovieList(view: RecyclerView, viewModel: MainViewModel) {
        RecyclerViewPaginator(
                recyclerView = view,
                isLoading = { viewModel.isLoading },
                loadMore = { viewModel.fetchNextMovieList() },
                onLast = { false }
        ).run {
            threshold = 8
        }
    }

    @JvmStatic
    @BindingAdapter("adapterMovieList")
    fun bindAdapterMovieList(view: RecyclerView, movieList: List<Movie>?) {
        movieList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<MovieListAdapter> { adapter ->
                adapter.setmovieList(itemList)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterCastList")
    fun bindAdapterCastList(view: RecyclerView, castList: List<Cast>?) {
        castList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<CastListAdapter> { adapter ->
                adapter.setCastList(itemList)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterReviewList")
    fun bindAdapterReviewList(view: RecyclerView, reviewList: List<Review>?) {
        reviewList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<ReviewerListAdapter> { adapter ->
                adapter.setCastList(itemList)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterSimilarList")
    fun bindSimilarMoviesList(view: RecyclerView, movieList: List<Movie>?) {
        movieList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<SimilarMoviesAdapter> { adapter ->
                adapter.setMovieList(itemList)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterProduction")
    fun bindMovieProductionList(view: RecyclerView, companyList: List<Company>?) {
        companyList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<CompanyAdapter> { adapter ->
                adapter.setCompanyList(itemList)
            }
        }
    }
}
