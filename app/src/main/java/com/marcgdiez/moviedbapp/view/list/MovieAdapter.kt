package com.marcgdiez.moviedbapp.view.list

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.marcgdiez.moviedbapp.Navigator
import com.marcgdiez.moviedbapp.R
import com.marcgdiez.moviedbapp.domain.bo.Movie
import com.marcgdiez.moviedbapp.extensions.inflate
import com.marcgdiez.moviedbapp.extensions.load
import kotlinx.android.synthetic.main.adapter_movie.view.*

class MovieAdapter(private val navigator: Navigator) :
        RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: MutableList<Movie> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(parent.inflate(R.layout.adapter_movie))

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>) {
        val previousSize = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeInserted(previousSize, this.movies.size)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position], navigator)

}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie, navigator: Navigator) = with(itemView) {
        ViewCompat.setTransitionName(backgroundImage, movie.id.toString())
        backgroundImage.load(movie.backdropPath)
        title.text = movie.name
        rating.text = movie.voteAverage.toString()
        setOnClickListener { navigator.navigateToDetail(movie, backgroundImage) }
    }
}