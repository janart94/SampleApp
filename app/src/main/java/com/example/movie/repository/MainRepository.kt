package com.example.movie.repository

import androidx.annotation.WorkerThread
import com.example.movie.mapper.ErrorResponseMapper
import com.example.movie.network.MovieClient
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val movieClient: MovieClient) : Repository {

    @WorkerThread
    fun fetchMovieList(
            page: Int,
            onStart: () -> Unit,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        val response = movieClient.fetchMovieList(page = page)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                response.results.forEach { movie -> movie.page = page + 1 }
                emit(response.results)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
                // handles the case when the API request gets an exception response.
                // e.g., network connection error.
                .onException { onError(message) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)


    @WorkerThread
    fun fetchNowPlayingMovieList(
            page: Int,
            onStart: () -> Unit,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        val response = movieClient.fetchNowPlayingList(page = page)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                emit(response.results)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
                // handles the case when the API request gets an exception response.
                // e.g., network connection error.
                .onException { onError(message) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
