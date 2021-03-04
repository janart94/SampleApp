package com.example.movie.ui.cast

import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.example.movie.base.LiveCoroutinesViewModel
import com.example.movie.model.Cast
import com.example.movie.model.Review
import com.example.movie.repository.ListRepository
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

class CastListViewModel @AssistedInject constructor(
        listRepository: ListRepository,
        @Assisted private val movieId: String
) : LiveCoroutinesViewModel() {

    private val castFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val castListLiveData: LiveData<List<Cast>>

    private val reviewFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
    val reviewListLiveData: LiveData<List<Review>>

    @get:Bindable
    var toastMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(false)
        private set


    init {
        castListLiveData = castFetchingIndex.asLiveData().switchMap { page ->
            listRepository.fetchCasting(
                    movieId,
                    onComplete = { isLoading = false },
                    onError = { toastMessage = it }
            ).asLiveDataOnViewModelScope()
        }


        reviewListLiveData = reviewFetchingIndex.asLiveData().switchMap { page ->
            listRepository.fetchReview(
                    movieId,
                    onComplete = { isLoading = false },
                    onError = { toastMessage = it }
            ).asLiveDataOnViewModelScope()
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(movieId: String): CastListViewModel
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
