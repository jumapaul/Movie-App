package com.pauljuma.movieapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var movies: MutableList<Result> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addMovies(items: List<Result>) {
        this.movies.addAll(items)
        notifyDataSetChanged()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Result) {
            val image = itemView.findViewById<ImageView>(R.id.movie_photo)
            val title1 = itemView.findViewById<TextView>(R.id.movie_title)
            val overView = itemView.findViewById<TextView>(R.id.movie_overview)
            val rate = itemView.findViewById<TextView>(R.id.movie_rating)
            title1.text = movie.title
            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(image)

            overView.text = movie.overview
            rate.text = "Rating: " + movie.vote_average.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
