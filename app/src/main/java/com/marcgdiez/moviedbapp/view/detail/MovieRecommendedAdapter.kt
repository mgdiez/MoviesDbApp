package com.marcgdiez.moviedbapp.view.detail

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.marcgdiez.moviedbapp.Navigator
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.inflate
import com.marcgdiez.moviedbapp.extensions.load
import kotlinx.android.synthetic.main.adapter_recommended_movie.view.*

class MovieRecommendedAdapter(val navigator: Navigator.NavigatorImpl) : RecyclerView.Adapter<MovieRecommendedViewHolder>() {

    private var movies: List<Movie> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecommendedViewHolder =
            MovieRecommendedViewHolder(parent.inflate(R.layout.adapter_recommended_movie))

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieRecommendedViewHolder, position: Int) {
        holder.bind(movies[position], navigator)
    }
}

class MovieRecommendedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie, navigator: Navigator) = with(itemView) {
        ViewCompat.setTransitionName(moviePoster, movie.id.toString())
        moviePoster.load(movie.imageUrl)
        rateAvg.text = movie.voteAverage.toString()
        setOnClickListener { navigator.navigateToDetail(movie, moviePoster) }
    }
}