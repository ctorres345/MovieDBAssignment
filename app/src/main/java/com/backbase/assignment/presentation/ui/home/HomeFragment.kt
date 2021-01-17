package com.backbase.assignment.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentHomeBinding
import com.backbase.assignment.model.MovieUIModel
import com.backbase.assignment.presentation.adapter.MovieAdapter
import com.backbase.assignment.presentation.base.BaseFragment
import com.backbase.assignment.presentation.ext.makeGone
import com.backbase.assignment.presentation.ext.makeVisible
import com.backbase.assignment.presentation.ext.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), MovieAdapter.Callback {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularMovieAdapter: MovieAdapter
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
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }
        binding.popularMovieList.layoutManager = LinearLayoutManager(requireContext())
        binding.popularMovieList.adapter = popularMovieAdapter
        viewModel.getPopularMovies()
    }

    override fun initObservers() {
        observe(viewModel.getViewState, ::onViewStateUpdated)
    }

    private fun onViewStateUpdated(viewState: HomeViewState?) {
        viewState?.let {
            when(it){
                is HomeViewState.GetPopularMoviesSuccess -> popularMovieAdapter.updateMovieList(it.movies)
                is HomeViewState.GetPopularMoviesError -> Toast.makeText(requireContext(), "Error Fetching Popular Movies", Toast.LENGTH_SHORT).show()
            }
            binding.progress.makeGone()
        }
    }

    override fun initAdapter() {
        popularMovieAdapter = MovieAdapter(this)
    }

    override fun onMovieSelected(movie: MovieUIModel) {
        Toast.makeText(requireContext(), "Movie Selected : ${movie.title}", Toast.LENGTH_LONG).show()
    }
}