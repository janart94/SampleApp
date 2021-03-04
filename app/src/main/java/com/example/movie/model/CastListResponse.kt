package com.example.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastListResponse(
        @field:Json(name = "cast") val cast: List<Cast>
)
