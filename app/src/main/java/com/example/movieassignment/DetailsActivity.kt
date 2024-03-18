package com.example.movieassignment

import android.R.attr.strokeWidth
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieassignment.apiFiles.ApiClient
import com.example.movieassignment.databinding.ActionOnDetailsActivity
import com.example.movieassignment.factory.MovieViewModelFactory
import com.example.movieassignment.repository.MovieRepository
import com.example.movieassignment.viewModel.MovieViewModel


class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var imdbId: String
    private lateinit var binding: ActionOnDetailsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActionOnDetailsActivity.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        // Retrieve IMDb ID from intent extras
        imdbId = intent.getStringExtra("imdbID")!!

        initViews()
        observeViewModel()
        // Make API call to fetch movie details
        fetchMovieDetails(imdbId)

    }

    private fun initViews() {
        val apiService = ApiClient.create()
        val repository = MovieRepository(apiService)
        viewModel = ViewModelProvider(this, MovieViewModelFactory(repository)).get(MovieViewModel::class.java)
    }

    private fun fetchMovieDetails(it: String): Unit {
        viewModel.movieDetail(it)
    }

    private fun observeViewModel() {
        viewModel.movieDetail.observe(this, Observer {

            if (it != null) {
                //setting texts
                try {
                    binding.title.text = it.Title
                    binding.genre.text = Html.fromHtml("<b>Genre: </b> ${it.Genre}")
                    binding.plot.text = Html.fromHtml("<b>Plot: </b> ${it.Plot}")
                    binding.language.text = Html.fromHtml("<b>Language: </b> ${it.Language}")
                    binding.release.text = Html.fromHtml("<b>Release Date: </b> ${it.Released}")
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                //setting image
                try {
                    Glide.with(this)
                        .load(it.Poster)
                        .into(binding.image)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                //setting constraint
                try {
                    val shape = GradientDrawable()
                    shape.shape = GradientDrawable.RECTANGLE
                    shape.cornerRadii = floatArrayOf(16F,16F,16F,16F,16F,16F,16F,16F)
                    shape.setColor(Color.parseColor("#ffffff"))
                    shape.setStroke(3, Color.parseColor("#e0d8d7"))
                    binding.watchNow.setBackground(shape)
                    binding.watchNow.text = Html.fromHtml("<b>Watch Now</b>")
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
        })
    }

}