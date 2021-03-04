package com.example.movie.repository

import androidx.annotation.WorkerThread
import com.example.movie.mapper.ErrorResponseMapper
import com.example.movie.network.ListClient
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

class ListRepository @Inject constructor(
        private val listClient: ListClient) : Repository {


    @WorkerThread
    fun fetchCasting(
            movieId: String,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        val response = listClient.fetchCasting(movieId = movieId)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                emit(response.cast)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }.onException { onError(message) }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)


    @WorkerThread
    fun fetchReview(
            movieId: String,
            onComplete: () -> Unit,
            onError: (String?) -> Unit
    ) = flow {
        val response = listClient.fetchReview(movieId = movieId)
        response.suspendOnSuccess {
            data.whatIfNotNull { response ->
                emit(response.results)
            }
        }.onError {
            map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }.onException { onError(message) }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
