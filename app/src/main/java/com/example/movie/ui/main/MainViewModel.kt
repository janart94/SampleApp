package com.example.movie.ui.main

import androidx.annotation.MainThread
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.movie.base.LiveCoroutinesViewModel
import com.example.movie.model.Movie
import com.example.movie.repository.MainRepository
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private val movieFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val movieListLiveData: LiveData<List<Movie>>

    private val nowPlayingFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val nowPlayingListLiveData: LiveData<List<Movie>>

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set

    init {

        movieListLiveData = movieFetchingIndex.asLiveData().switchMap { page ->
            mainRepository.fetchMovieList(
                    page = page,
                    onStart = { isLoading = true },
                    onComplete = { isLoading = false },
                    onError = { toastMessage = it }
            ).asLiveDataOnViewModelScope()
        }

        nowPlayingListLiveData = nowPlayingFetchingIndex.asLiveData().switchMap { page ->
            mainRepository.fetchNowPlayingMovieList(
                    page = page,
                    onStart = { isLoading = true },
                    onComplete = { isLoading = false },
                    onError = { toastMessage = it }
            ).asLiveDataOnViewModelScope()
        }
    }

    @MainThread
    fun fetchNextMovieList() {
        if (!isLoading) {
            movieFetchingIndex.value++
        }
    }
}
