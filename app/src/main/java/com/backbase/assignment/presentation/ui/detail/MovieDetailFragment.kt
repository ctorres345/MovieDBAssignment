package com.backbase.assignment.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
import com.backbase.assignment.presentation.ext.*
import com.backbase.assignment.util.BundleParams
import com.backbase.assignment.util.ErrorDialog
import com.backbase.assignment.util.UIVisibilityComponents
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : BaseDialogFragment(), UIVisibilityComponents {
    @Inject
    lateinit var errorDialog: ErrorDialog
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    override fun getTheme(): Int {
        return R.style.TraslucentDialog
    }

    override fun initUI() {
        hideContent()
        showLoading()
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.genreList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.genreList.addItemDecoration(HorizontalSpacingDecorator(16))
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
            hideLoading()
            when(it) {
                is MovieDetailViewState.GetMovieDetailSuccess -> {
                    updateUIWithDetail(it.detail)
                    showContent()
                }
                is MovieDetailViewState.GetMovieDetailError -> {
                    errorDialog.showErrorMessage(
                        message = requireContext().getString(R.string.generic_error_message),
                        negativeActionName = requireContext().getString(R.string.try_again_label),
                        negativeAction = {
                            showLoading()
                            hideContent()
                            viewModel.getMovieDetail(movieId!!)
                        }
                    )
                }
            }
        }
    }

    private fun updateUIWithDetail(detail: MovieDetailUIModel) {
        val duration = detail.getHourAndMinutesRuntime()?.let {
            "${it.first}h ${it.second}m"
        } ?: ""
        with(detail){
            Glide.with(binding.root)
                .load(posterPath)
                .placeholder(ContextCompat.getDrawable(requireContext(), R.drawable.ic_movie_poster_placeholder))
                .into(binding.poster)
            binding.movieTitle.text = title
            binding.overview.text = overview
            binding.movieDetail.text = requireContext().getString(R.string.movie_detail_movie_date_duration, releaseDate, duration)
            binding.genreList.adapter = GenreAdapter(genres)
        }
    }

    override fun showLoading() {
        binding.loading.apply {
            makeVisible()
            playAnimation()
        }
    }

    override fun hideLoading() {
        binding.loading.apply {
            cancelAnimation()
            makeGone()
        }
    }

    override fun showContent() {
        if(binding.content.isInvisible()){
            binding.content.makeVisible()
        }
    }

    override fun hideContent() {
        if(binding.content.isVisible()) {
            binding.content.makeInvisible()
        }
    }
}