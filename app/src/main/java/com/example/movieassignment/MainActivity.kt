package com.example.movieassignment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieassignment.adapter.MovieAdapter
import com.example.movieassignment.apiFiles.ApiClient
import com.example.movieassignment.databinding.ActionMainBinding
import com.example.movieassignment.factory.MovieViewModelFactory
import com.example.movieassignment.repository.MovieRepository
import com.example.movieassignment.viewModel.MovieViewModel

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var binding: ActionMainBinding

    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActionMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        //initialize views
        initViews()
        //observe View Model
        observeViews()

        //pagination
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        currentPage++
                        viewModel.searchMovies(binding.editText.text.toString(), currentPage)
                    }
                }
            })
        }

        //edit text change listener
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.length >= 3) { // Minimum 3 characters to trigger search
                    movieAdapter.clearMovies()
                    currentPage = 1 // Reset page number for new searches
                    viewModel.searchMovies(binding.editText.text.toString(), currentPage)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }


    private fun initViews() {
        try {
            val apiService = ApiClient.create()
            val repository = MovieRepository(apiService)
            viewModel = ViewModelProvider(this, MovieViewModelFactory(repository)).get(MovieViewModel::class.java)

            movieAdapter = MovieAdapter(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun observeViews() {
        viewModel.movies.observe(this, Observer {
            if (it != null) {
                binding.notext.visibility = View.GONE
                movieAdapter.setMovies(it)
            } else if (movieAdapter.itemCount == 0) {
                binding.notext.visibility = View.VISIBLE
            }
        })

        viewModel.searchMovies("Marvel", currentPage) // Example query
    }

    override fun onItemClick(imdbID: String) {
        try {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("imdbID", imdbID)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
