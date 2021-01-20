package com.backbase.assignment.presentation.ui.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieDetailBinding
import com.backbase.assignment.mapper.getHourAndMinutesRuntime
import com.backbase.assignment.model.MovieDetailUIModel
import com.backbase.assignment.presentation.adapter.GenreAdapter
import com.backbase.assignment.presentation.adapter.decorator.HorizontalSpacingDecorator
import com.backbase.assignment.presentation.base.BaseDialogFragment
import com.backbase.assignment.presentation.base.BaseFragment
import com.backbase.assignment.presentation.ext.makeGone
import com.backbase.assignment.presentation.ext.makeVisible
import com.backbase.assignment.presentation.ext.observe
import com.backbase.assignment.util.BundleParams
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseDialogFragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel : MovieDetailViewModel by viewModels()
    private var movieId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.TraslucentDialog
    }

    override fun initUI() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.genreList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genreList.addItemDecoration(HorizontalSpacingDecorator(16))
        binding.progress.makeVisible()
        movieId?.let {
            viewModel.getMovieDetail(it)
        }
    }

    override fun initArguments() {
        arguments?.getInt(BundleParams.MOVIE_ID)?.let {
            movieId = it
        }
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(viewState: MovieDetailViewState?) {
        viewState?.let {
            when(it) {
                is MovieDetailViewState.GetMovieDetailSuccess -> updateUIWithDetail(it.detail)
                is MovieDetailViewState.GetMovieDetailError -> {
                    Toast.makeText(requireContext(), "Unexpected error retreiving detail", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }
            binding.progress.makeGone()
        }
    }

    private fun updateUIWithDetail(detail: MovieDetailUIModel) {
        val duration = detail.getHourAndMinutesRuntime()?.let {
            "${it.first}h ${it.second}m"
        } ?: ""
        with(detail){
            Glide.with(binding.root).load(posterPath).into(binding.poster)
            binding.movieTitle.text = title
            binding.overview.text = overview
            binding.movieDetail.text = "${releaseDate} - ${duration}"
            binding.genreList.adapter = GenreAdapter(genres)
        }
    }
}