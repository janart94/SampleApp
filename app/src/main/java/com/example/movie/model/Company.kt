package com.example.movie.model

import com.example.movie.constant.ApiConstant.IMAGE_BASE_URL
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
        @field:Json(name = "id") val id: Long,
        @field:Json(name = "logo_path") val logo_path: String?,
        @field:Json(name = "name") val name: String?
) {

    fun getImageUrl(): String {
        val index = logo_path?.split("/".toRegex())?.last()
        return "$IMAGE_BASE_URL$index"
    }

    fun getCompanyName(): String {
        return if (name.isNullOrEmpty()) "N/A" else name
    }
}