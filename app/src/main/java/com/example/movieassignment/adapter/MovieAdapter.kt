package com.example.movieassignment.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieassignment.R
import com.example.movieassignment.models.Search

class MovieAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies: MutableList<Search> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(movies[position].imdbID)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.movieTitleTextView)
        private val yearTextView: TextView = itemView.findViewById(R.id.movieYearTextView)
        private val moviePosterImageView: ImageView = itemView.findViewById(R.id.moviePosterImageView)

        fun bind(movie: Search) {
            try {
                titleTextView.text = movie.Title
                yearTextView.text = movie.Year
                // Load image using Glide
                Glide.with(itemView.context)
                    .load(movie.Poster)
                    .into(moviePosterImageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int = movies.size

    fun setMovies(newMovies: List<Search>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    fun clearMovies(){
        movies.clear()
    }

    //interface to implement click listener
    interface OnItemClickListener {
        fun onItemClick(imdbID: String)
    }

}
