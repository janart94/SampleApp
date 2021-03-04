package com.example.movie.model

import android.os.Parcelable
import com.example.movie.constant.ApiConstant.IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class AuthorDetail(
        @field:Json(name = "name") val name: String?,
        @field:Json(name = "avatar_path") val url: String?,
) : Parcelable {

    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.last()
        return "$IMAGE_BASE_URL$index"
    }

    fun getAuthorName(): String {
        return if (name.isNullOrEmpty()) "N/A" else name
    }

}
