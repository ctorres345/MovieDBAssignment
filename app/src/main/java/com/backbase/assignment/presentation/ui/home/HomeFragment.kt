package com.backbase.assignment.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentHomeBinding
import com.backbase.assignment.presentation.adapter.BillboardAdapter
import com.backbase.assignment.presentation.adapter.PopularMovieAdapter
import com.backbase.assignment.presentation.adapter.decorator.VerticalSpacingDecorator
import com.backbase.assignment.presentation.adapter.paging.VerticalPagingHandler
import com.backbase.assignment.presentation.base.BaseFragment
import com.backbase.assignment.presentation.ext.*
import com.backbase.assignment.util.BundleParams
import com.backbase.assignment.util.ErrorDialog
import com.backbase.assignment.util.UIVisibilityComponents
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), UIVisibilityComponents {
    @Inject
    lateinit var errorDialog: ErrorDialog
    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularMovieAdapter: PopularMovieAdapter
    private lateinit var billboardAdapter: BillboardAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }

    override fun initUI() {
        hideContent()
        showLoading()
        binding.popularMovieList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalSpacingDecorator(16))
            adapter = popularMovieAdapter
            val pagingHandler = VerticalPagingHandler(layoutManager as LinearLayoutManager) {
                showLoading()
                viewModel.getNextPopularMoviePage()
            }
            addOnScrollListener(pagingHandler)
        }
        binding.billboardList.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = billboardAdapter
        }
        viewModel.getMovies()
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(viewState: HomeViewState?) {
        viewState?.let {
            hideLoading()
            when(it){
                is HomeViewState.GetMoviesSuccess -> {
                    showContent()
                    billboardAdapter.updateMovieList(it.billboardMovies)
                    popularMovieAdapter.updateMovieList(it.popularMovies)
                }
                is HomeViewState.GetPopularMoviesSuccess -> popularMovieAdapter.updateMovieList(it.movies)
                is HomeViewState.GetPaginatedMoviesError -> errorDialog.showErrorMessage(
                    message = requireContext().getString(R.string.generic_error_message),
                    negativeActionName = requireContext().getString(R.string.try_again_label),
                    negativeAction = {
                        showLoading()
                        viewModel.getNextPopularMoviePage()
                    }
                )
                is HomeViewState.GetPopularMoviesError,
                is HomeViewState.GetBillboardMoviesError -> errorDialog.showErrorMessage(
                    message = requireContext().getString(R.string.generic_error_message),
                    negativeActionName = requireContext().getString(R.string.try_again_label),
                    negativeAction = {
                        showLoading()
                        hideContent()
                        viewModel.getMovies()
                    }
                )
            }
        }
    }

    override fun initAdapter() {
        popularMovieAdapter = PopularMovieAdapter {
            val bundle = bundleOf(BundleParams.MOVIE_ID to it.id)
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
        }
        billboardAdapter = BillboardAdapter()
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