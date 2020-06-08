package com.example.movieapptask.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.model.Movie
import com.example.movieapptask.R
import com.example.movieapptask.util.TMDB_IMAGEURL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie_main.view.*
class MoviesAdapter: PagedListAdapter<Movie, MoviesAdapter.MainViewHolder>(
    Callback
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindMovie(position)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movie_imageview: ImageView
        val title_textview: TextView
        var release_date_textview:TextView
        init {
            movie_imageview = itemView.movie_imageview
            title_textview = itemView.title_textview
            release_date_textview = itemView.release_date_textview
        }

        fun bindMovie(pos: Int){
            val movie = getItem(pos)
            movie?.let {
                title_textview.setText(movie.title)
                release_date_textview.setText(movie.releaseDate)
                movie.posterPath?.let {
                    if (it.isEmpty() || it.isBlank()) {
                        movie_imageview.setImageDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.ic_local_movies_gray
                            )
                        )
                    } else {
                        Picasso.get().load(TMDB_IMAGEURL + movie.posterPath).placeholder( R.drawable.ic_local_movies_gray).into(movie_imageview)
                    }
                }

                itemView.setOnClickListener {
                    val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(movie)
                    Navigation.findNavController(itemView).navigate(action)
                }
            }
        }
    }

    companion object {
        private val Callback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title
        }
    }
}