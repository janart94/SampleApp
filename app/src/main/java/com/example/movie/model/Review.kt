package com.example.movie.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Review(
        @field:Json(name = "content") val content: String?,
        @field:Json(name = "author_details") val author_details: AuthorDetail?,
        @field:Json(name = "created_at") val date: String?,
) : Parcelable
