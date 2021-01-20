package com.backbase.assignment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ItemMovieBinding
import com.backbase.assignment.model.PopularMovieUIModel
import com.backbase.assignment.presentation.ext.setDebounceOnClickListener
import com.bumptech.glide.Glide

class PopularMovieAdapter(
    private var movieList: MutableList<PopularMovieUIModel> = mutableListOf(),
    private val onMovieSelectedListener: (PopularMovieUIModel) -> Unit
) : RecyclerView.Adapter<PopularMovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun updateMovieList(newList: List<PopularMovieUIModel>) {
        movieList.apply {
            addAll(newList)
        }
        notifyDataSetChanged()
    }


    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovieUIModel) {
            binding.movieTitle.text = movie.title
            binding.releaseDate.text = movie.releaseDate
            binding.ratingProgress.setProgress(movie.progressPopularity)
            binding.rating.text = movie.roundedPopularity.toString()
            Glide.with(binding.root)
                .load(movie.posterImage)
                .placeholder(R.drawable.ic_movie_poster_placeholder)
                .into(binding.poster)
            binding.root.setDebounceOnClickListener {
                onMovieSelectedListener.invoke(movie)
            }
        }
    }
}