package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.ItemPosterBinding
import com.example.movie.model.Movie
import com.example.movie.ui.viewholder.PosterViewHolder
import com.example.movie.utils.Utils
import com.skydoves.bindables.binding

class ViewPagerAdapter : RecyclerView.Adapter<PosterViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val binding = parent.binding<ItemPosterBinding>(R.layout.item_poster)
        return PosterViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                        ?: return@setOnClickListener
                Utils.movieToDetailScreen(binding.cardView.context, items[position])
            }
        }
    }

    fun setPosterList(postList: List<Movie>) {
        val previousItemSize = items.size
        items.addAll(postList)
        notifyItemRangeChanged(previousItemSize, postList.size)
    }

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.binding.apply {
            movie = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size
}

