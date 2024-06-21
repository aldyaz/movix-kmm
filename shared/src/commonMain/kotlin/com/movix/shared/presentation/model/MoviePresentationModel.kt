package com.movix.shared.presentation.model

data class MoviePresentationModel(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val genres: List<String>
)