package com.example.movieassignment.repository

import com.example.movieassignment.apiFiles.ApiService
import com.example.movieassignment.models.DetailData
import com.example.movieassignment.models.MovieDataResponse
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) {

    suspend fun searchMovies(query: String, page: Int): Response<MovieDataResponse> {
        return apiService.searchMovies("f20179bc", query, page)
    }

    suspend fun movieDetail(query: String): Response<DetailData> {
        return apiService.movieDetail("f20179bc", query)
    }
}
