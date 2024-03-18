package com.example.movieassignment.models

data class MovieDataResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)