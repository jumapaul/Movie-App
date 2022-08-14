package com.pauljuma.movieapp

data class PopularMovies(
    val page: Int,
    val results: List<Result>
)