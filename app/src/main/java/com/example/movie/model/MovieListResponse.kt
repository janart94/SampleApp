package com.example.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
        @field:Json(name = "page") val page: Int,
        @field:Json(name = "total_results") val total_results: Int,
        @field:Json(name = "total_pages") val total_pages: Int,
        @field:Json(name = "results") val results: List<Movie>
)
