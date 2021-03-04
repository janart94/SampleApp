package com.example.movie.ui.details

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.example.movie.base.LiveCoroutinesViewModel
import com.example.movie.model.Movie
import com.example.movie.model.MovieInfo
import com.example.movie.repository.DetailRepository
import com.example.movie.utils.Utils
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel @AssistedInject constructor(
        detailRepository: DetailRepository,
        @Assisted private val movieId: String
) : LiveCoroutinesViewModel() {

    val movieInfoLiveData: LiveData<MovieInfo?>

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set


    private val similarMovieListIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val similarMovieLiveData: LiveData<List<Movie>>

    init {
        movieInfoLiveData = detailRepository.fetchMovieData(
                movieId = movieId,
                onComplete = { isLoading = false },
                onError = { toastMessage = it }
        ).asLiveDataOnViewModelScope()

        similarMovieLiveData = similarMovieListIndex.asLiveData().switchMap { page ->
            detailRepository.fetchSimilarMovies(
                    movieId = movieId,
                    onComplete = { isLoading = false },
                    onError = { toastMessage = it }
            ).asLiveDataOnViewModelScope()
        }
    }

    fun toCastScreen(view: View) {
        Utils.movieToCastListScreen(view.context, movieId)
    }

    fun toReviewerScreen(view: View) {
        Utils.movieToReviewerListScreen(view.context, movieId)
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(movieId: String): DetailViewModel
    }


    companion object {
        fun provideFactory(
                assistedFactory: AssistedFactory,
                movieId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(movieId) as T
            }
        }
    }
}
