package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.movie.R
import com.example.movie.databinding.ItemMovieBinding
import com.example.movie.model.Movie
import com.example.movie.ui.viewholder.MovieViewHolder
import com.example.movie.utils.Utils
import com.skydoves.bindables.binding

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = parent.binding<ItemMovieBinding>(R.layout.item_movie)
        return MovieViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
                        ?: return@setOnClickListener
                Utils.movieToDetailScreen(binding.cardView.context, items[position])
            }
        }
    }

    fun setmovieList(movieList: List<Movie>) {
        val previousItemSize = items.size
        items.addAll(movieList)
        notifyItemRangeChanged(previousItemSize, movieList.size)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            movie = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}
