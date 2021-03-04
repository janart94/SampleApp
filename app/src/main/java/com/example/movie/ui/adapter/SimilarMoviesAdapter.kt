package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.movie.R
import com.example.movie.databinding.ItemSimilarBinding
import com.example.movie.model.Movie
import com.example.movie.ui.viewholder.SimilarMovieViewHolder
import com.example.movie.utils.Utils
import com.skydoves.bindables.binding

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMovieViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding = parent.binding<ItemSimilarBinding>(R.layout.item_similar)
        return SimilarMovieViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != NO_POSITION }
                        ?: return@setOnClickListener
                Utils.movieToDetailScreen(binding.cardView.context, items[position])
            }
        }
    }

    fun setMovieList(movieList: List<Movie>) {
        val previousItemSize = items.size
        items.addAll(movieList)
        notifyItemRangeChanged(previousItemSize, movieList.size)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.binding.apply {
            movie = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}
