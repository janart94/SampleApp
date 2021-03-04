package com.example.movie.repository

import androidx.annotation.WorkerThread
import com.example.movie.mapper.ErrorResponseMapper
import com.example.movie.model.MovieInfo
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
import javax.inject.Inject

class DetailRepository @Inject constructor(
        private val movieClient: MovieClient) : Repository {

    @WorkerThread
    fun fetchMovieData(
            movieId: String,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow<MovieInfo?> {
        val response = movieClient.fetchMovieDetail(movieId = movieId)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                emit(response)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }.onException { onError(message) }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun fetchSimilarMovies(
            movieId: String,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        val response = movieClient.fetchSimilarMovies(movieId = movieId)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                emit(response.results)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }.onException { onError(message) }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
