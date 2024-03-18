package com.example.movieassignment.apiFiles

import com.example.movieassignment.models.DetailData
import com.example.movieassignment.models.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchString: String,
        @Query("page") page: Int
    ): Response<MovieDataResponse>

    @GET("/")
    suspend fun movieDetail(
        @Query("apikey") apiKey: String,
        @Query("i") imdbID: String
    ): Response<DetailData>
}

