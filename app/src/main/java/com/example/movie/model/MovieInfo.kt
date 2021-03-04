package com.example.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieInfo(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "popularity") val popularity: String?,
        @field:Json(name = "overview") val overview: String?,
        @field:Json(name = "status") val release_status: String?,
        @field:Json(name = "production_companies") val productionCompanies: List<Company>
) {

    fun getPopularityValue(): String {
        return if (popularity.isNullOrEmpty()) "N/A" else popularity
    }

    fun getOverviewValue(): String {
        return if (overview.isNullOrEmpty()) "N/A" else overview
    }

    fun getReleaseData(): String {
        return if (release_status.isNullOrEmpty()) "N/A" else release_status
    }


}
