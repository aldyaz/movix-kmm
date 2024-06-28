package com.movix.shared.domain.model

data class MovieDomainModel(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: Double,
    val runtime: Int
)