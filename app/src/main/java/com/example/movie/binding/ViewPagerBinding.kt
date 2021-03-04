package com.example.movie.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.movie.model.Movie
import com.example.movie.ui.adapter.ViewPagerAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object ViewPagerBinding {


    @JvmStatic
    @BindingAdapter("adapterPoster")
    fun bindAdaptermovieList(view: ViewPager2, movieList: List<Movie>?) {
        movieList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<ViewPagerAdapter> { adapter ->
                adapter.setPosterList(itemList)
            }
        }
    }
}
