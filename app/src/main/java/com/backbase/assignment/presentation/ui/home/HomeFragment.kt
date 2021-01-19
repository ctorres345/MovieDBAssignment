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
import com.backbase.assignment.presentation.ext.makeGone
import com.backbase.assignment.presentation.ext.makeVisible
import com.backbase.assignment.presentation.ext.observe
import com.backbase.assignment.util.BundleParams
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
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

    override fun initUI() {
        binding.progress.makeVisible()
        binding.popularMovieList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(VerticalSpacingDecorator(16))
            adapter = popularMovieAdapter
            val pagingHandler = VerticalPagingHandler(layoutManager as LinearLayoutManager) {
                binding.progress.makeVisible()
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
            when(it){
                is HomeViewState.GetMoviesSuccess -> {
                    billboardAdapter.updateMovieList(it.billboardMovies)
                    popularMovieAdapter.updateMovieList(it.popularMovies)
                }
                is HomeViewState.GetPopularMoviesSuccess -> popularMovieAdapter.updateMovieList(it.movies)
                is HomeViewState.GetPopularMoviesError -> Toast.makeText(requireContext(), "Error Fetching Popular Movies", Toast.LENGTH_SHORT).show()
                is HomeViewState.GetBillboardMoviesError -> Toast.makeText(requireContext(), "Error Fetching Billboard Movies", Toast.LENGTH_SHORT).show()
            }
            binding.progress.makeGone()
        }
    }

    override fun initAdapter() {
        popularMovieAdapter = PopularMovieAdapter {
            val bundle = bundleOf(BundleParams.MOVIE_ID to it.id)
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
        }
        billboardAdapter = BillboardAdapter()
    }
}