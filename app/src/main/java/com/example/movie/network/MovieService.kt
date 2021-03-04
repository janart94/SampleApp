package com.example.movie.network

import com.example.movie.model.MovieInfo
import com.example.movie.model.MovieListResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchMovieList(
            @Query("page") page: Int = 0,
            @Query("language") language: String = "en-US",
            @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"
    ): ApiResponse<MovieListResponse>

    @GET("movie/now_playing")
    suspend fun fetchNowPlaying(
            @Query("page") page: Int = 0,
            @Query("language") language: String = "en-US",
            @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"
    ): ApiResponse<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(@Path("movie_id") movieId: String,
                                 @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"): ApiResponse<MovieInfo>

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(@Path("movie_id") movieId: String,
                                   @Query("api_key") api_key: String = "45bf6592c14a965b33549f4cc7e6c664"): ApiResponse<MovieListResponse>
}
