package com.example.movie.network

import com.example.movie.model.MovieInfo
import com.example.movie.model.MovieListResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MovieClient @Inject constructor(
        private val movieService: MovieService
) {

    suspend fun fetchMovieList(
            page: Int
    ): ApiResponse<MovieListResponse> =
            movieService.fetchMovieList(page = page + 1, )

    suspend fun fetchNowPlayingList(
            page: Int
    ): ApiResponse<MovieListResponse> =
            movieService.fetchNowPlaying(page = page + 1, )

    suspend fun fetchMovieDetail(
            movieId: String
    ): ApiResponse<MovieInfo> =
            movieService.fetchMovieDetail(movieId = movieId)

    suspend fun fetchSimilarMovies(
            movieId: String
    ): ApiResponse<MovieListResponse> =
            movieService.fetchSimilarMovies(movieId = movieId)
}
