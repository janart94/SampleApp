package com.example.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewListResponse(
        @field:Json(name = "results") val results: List<Review>
)
