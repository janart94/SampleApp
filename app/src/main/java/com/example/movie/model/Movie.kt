package com.example.movie.model

import android.os.Parcelable
import com.example.movie.constant.ApiConstant.IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(
        var page: Int = 0,
        @field:Json(name = "id") val id: String,
        @field:Json(name = "original_language") val original_language: String?,
        @field:Json(name = "vote_average") val vote_average: Float,
        @field:Json(name = "release_date") val release_date: String?,
        @field:Json(name = "title") val title: String?,
        @field:Json(name = "poster_path") val url: String?,
        @field:Json(name = "backdrop_path") val bgUrl: String?
) : Parcelable {

    fun getLaunguageName(): String {
        return if (original_language.isNullOrEmpty()) "N/A" else original_language
    }

    fun getVoteValue(): Float {
        return if (vote_average <= 0) 0f else vote_average
    }

    fun getReleaseDate(): String {
        return if (release_date.isNullOrEmpty()) "N/A" else release_date
    }

    fun getTitleValue(): String {
        return if (title.isNullOrEmpty()) "N/A" else title
    }

    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.last()
        return "$IMAGE_BASE_URL$index"
    }

    fun getBgImageUrl(): String {
        val index = bgUrl?.split("/".toRegex())?.last()
        return "$IMAGE_BASE_URL$index"
    }
}
