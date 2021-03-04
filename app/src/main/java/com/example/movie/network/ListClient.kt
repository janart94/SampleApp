package com.example.movie.network

import com.example.movie.model.CastListResponse
import com.example.movie.model.ReviewListResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ListClient @Inject constructor(
        private val listService: ListService
) {

    suspend fun fetchCasting(
            movieId: String
    ): ApiResponse<CastListResponse> =
            listService.fetchCasting(movieId)

    suspend fun fetchReview(
            movieId: String
    ): ApiResponse<ReviewListResponse> =
            listService.fetchMovieReview(movieId)

}
