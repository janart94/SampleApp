package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.ItemReviewerBinding
import com.example.movie.model.Review
import com.example.movie.ui.viewholder.ReviewerViewHolder
import com.skydoves.bindables.binding

class ReviewerListAdapter : RecyclerView.Adapter<ReviewerViewHolder>() {

    private val items: MutableList<Review> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewerViewHolder {

        val binding = parent.binding<ItemReviewerBinding>(R.layout.item_reviewer)
        return ReviewerViewHolder(binding)

    }

    fun setCastList(movieList: List<Review>) {
        val previousItemSize = items.size
        items.addAll(movieList)
        notifyItemRangeChanged(previousItemSize, movieList.size)
    }

    override fun onBindViewHolder(holder: ReviewerViewHolder, position: Int) {
        holder.binding.apply {
            review = items[position]
            executePendingBindings()
        }
    }


    override fun getItemCount() = items.size
}
