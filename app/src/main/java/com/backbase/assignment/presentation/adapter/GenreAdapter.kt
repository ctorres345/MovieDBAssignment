package com.backbase.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.databinding.ItemGenreBinding
import com.backbase.assignment.model.MovieGenreUIModel

class GenreAdapter(
    private var movieList: List<MovieGenreUIModel>
) : RecyclerView.Adapter<GenreAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: MovieGenreUIModel) {
            binding.genre.text = genre.name
        }
    }
}