package com.backbase.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ItemBillboardMovieBinding
import com.backbase.assignment.model.BillboardMovieUIModel
import com.bumptech.glide.Glide

class BillboardAdapter(
    private var movieList: MutableList<BillboardMovieUIModel> = mutableListOf()
) : RecyclerView.Adapter<BillboardAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemBillboardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun updateMovieList(newList: List<BillboardMovieUIModel>) {
        movieList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemBillboardMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: BillboardMovieUIModel) {
            Glide.with(binding.root)
                .load(movie.posterImage)
                .placeholder(R.drawable.ic_movie_poster_placeholder)
                .into(binding.poster)
        }
    }
}