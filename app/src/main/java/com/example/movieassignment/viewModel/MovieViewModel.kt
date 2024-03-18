package com.example.movieassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieassignment.models.DetailData
import com.example.movieassignment.models.Search
import com.example.movieassignment.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<Search>>()
    val movies: LiveData<List<Search>> = _movies

    private val _movieDetail = MutableLiveData<DetailData>()
    val movieDetail: LiveData<DetailData> = _movieDetail

    fun searchMovies(query: String, page: Int) {
        viewModelScope.launch {
            try {
                val moviesResponse = repository.searchMovies(query, page)
                if(moviesResponse.isSuccessful){
                    moviesResponse.body()?.let {
                        // Set the list of movies to the LiveData
                        _movies.postValue(it.Search)
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }

    }


    fun movieDetail(query: String) {
        viewModelScope.launch {
            try {
                val moviesResponse = repository.movieDetail(query)
                if(moviesResponse.isSuccessful){
                    moviesResponse.body()?.let {
                        // Set the details to the LiveData
                        _movieDetail.postValue(it)
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
                e.printStackTrace()
            }
        }

    }
}
