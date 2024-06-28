package com.movix.shared.presentation.model

data class MoviePresentationModel(
    val id: Long = 0L,
    val title: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val genres: List<String> = emptyList(),
    val rating: Float = 0f,
    val ratingStar: Float = 0f,
    val duration: String = ""
)