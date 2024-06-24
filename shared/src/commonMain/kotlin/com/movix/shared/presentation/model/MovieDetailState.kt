package com.movix.shared.presentation.model

data class MovieDetailState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val data: MoviePresentationModel = MoviePresentationModel()
)