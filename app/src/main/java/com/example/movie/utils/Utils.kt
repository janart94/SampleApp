package com.example.movie.utils

import android.content.Context
import android.content.Intent
import com.example.movie.model.Movie
import com.example.movie.ui.cast.CastListActivity
import com.example.movie.ui.details.DetailActivity
import com.example.movie.ui.review.ReviewListActivity

class Utils {

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun movieToDetailScreen(context: Context, movie: Movie) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(intent)
        }

        fun movieToCastListScreen(context: Context, movieId: String) {
            val intent = Intent(context, CastListActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            context.startActivity(intent)
        }

        fun movieToReviewerListScreen(context: Context, movieId: String) {
            val intent = Intent(context, ReviewListActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }


}