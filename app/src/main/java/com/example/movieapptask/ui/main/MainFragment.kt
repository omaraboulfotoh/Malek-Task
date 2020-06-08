package com.example.movieapptask.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.movieapptask.R
import com.example.movieapptask.app.MovieApp
import com.example.movieapptask.dagger.viewmodelfactory.ViewModelFactory
import com.example.movieapptask.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var moviesViewModel: MoviesViewModel
    private val moviesAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MovieApp).appComponent.inject(this)
        moviesViewModel = viewModelFactory.create(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        movies_recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = moviesAdapter
        }

        moviesViewModel.movies.observe(requireActivity(), Observer {
            if (!it.isNullOrEmpty()){
                movies_recyclerView.visibility = View.VISIBLE
                moviesAdapter.submitList(it)
                no_movies_layout.visibility = View.GONE
            }else{
                no_movies_layout.visibility = View.VISIBLE
                movies_recyclerView.visibility = View.GONE
            }

        })
    }
}
