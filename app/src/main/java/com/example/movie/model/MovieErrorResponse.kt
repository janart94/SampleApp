package com.example.movie.model

/**
 * A customized movie error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class MovieErrorResponse(
        val code: Int,
        val message: String?
)
