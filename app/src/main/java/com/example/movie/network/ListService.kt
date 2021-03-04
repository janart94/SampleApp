package com.example.movie.network

import com.example.movie.model.CastListResponse
import com.example.movie.model.ReviewListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListService {

    @GET("movie/{movie_id}/credits")
    suspend fun fetchCasting(@Path("movie_id") movieId: String,
                             @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"): ApiResponse<CastListResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReview(@Path("movie_id") movieId: String,
                                 @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"): ApiResponse<ReviewListResponse>
}
