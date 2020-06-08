package com.example.movieapptask.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.example.movieapptask.R
import com.example.movieapptask.util.TMDB_IMAGEURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {
    val args: DetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val movie = args.movie
        Picasso.get().load(TMDB_IMAGEURL + movie.backdropPath)
            .placeholder(R.drawable.ic_local_movies_gray).into(movie_imageview)
        movie_title.text = getString(R.string.title) + movie.title
        movie_rating.text = getString(R.string.rating) + movie.voteAverage.toString()
        movie_release_date.text = getString(R.string.releaseDate) + movie.releaseDate
        movie_overview.text = getString(R.string.overview) + movie.overview
    }
}
