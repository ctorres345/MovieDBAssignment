package com.backbase.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.databinding.ItemMovieBinding
import com.backbase.assignment.model.MovieUIModel

class MovieAdapter(
    private val callback: Callback,
    private var movieList: MutableList<MovieUIModel> = mutableListOf()
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun updateMovieList(newList: List<MovieUIModel>) {
        movieList.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUIModel) {
            binding.movieTitle.text = movie.title
            binding.releaseDate.text = movie.releaseDate
            binding.duration.text = movie.duration
            binding.root.setOnClickListener {
                callback.onMovieSelected(movie)
            }
        }
    }

    interface Callback {
        fun onMovieSelected(movie: MovieUIModel)
    }
}