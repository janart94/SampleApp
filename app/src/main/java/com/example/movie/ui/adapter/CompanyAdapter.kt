package com.example.movie.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.ItemCompanyBinding
import com.example.movie.model.Company
import com.example.movie.ui.viewholder.CompanyViewHolder
import com.skydoves.bindables.binding

class CompanyAdapter : RecyclerView.Adapter<CompanyViewHolder>() {

    private val items: MutableList<Company> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        val binding = parent.binding<ItemCompanyBinding>(R.layout.item_company)
        return CompanyViewHolder(binding)
    }

    fun setCompanyList(movieList: List<Company>) {
        val previousItemSize = items.size
        items.addAll(movieList)
        notifyItemRangeChanged(previousItemSize, movieList.size)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        holder.binding.apply {
            company = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
}
