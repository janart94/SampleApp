package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.ItemCastBinding
import com.example.movie.model.Cast
import com.example.movie.ui.viewholder.CastViewHolder
import com.skydoves.bindables.binding

class CastListAdapter : RecyclerView.Adapter<CastViewHolder>() {

    private val items: MutableList<Cast> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = parent.binding<ItemCastBinding>(R.layout.item_cast)
        return CastViewHolder(binding)
    }

    fun setCastList(movieList: List<Cast>) {
        val previousItemSize = items.size
        items.addAll(movieList)
        notifyItemRangeChanged(previousItemSize, movieList.size)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.binding.apply {
            cast = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}
