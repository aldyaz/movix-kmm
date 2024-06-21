package com.movix.shared.presentation.model

data class DiscoverMovieViewState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val items: List<MoviePresentationModel> = listOf()
)