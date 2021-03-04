package com.example.movie.model

import android.os.Parcelable
import com.example.movie.constant.ApiConstant
import com.example.movie.constant.ApiConstant.IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Cast(
        @field:Json(name = "known_for_department") val department: String?,
        @field:Json(name = "name") val name: String?,
        @field:Json(name = "character") val character: String?,
        @field:Json(name = "profile_path") val url: String?,
) : Parcelable {

    fun getCastName(): String {
        return if (name.isNullOrEmpty()) "N/A" else name
    }

    fun getCharacterName(): String {
        return if (character.isNullOrEmpty()) "N/A" else character
    }

    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.last()
        return "$IMAGE_BASE_URL$index"
    }

}
