package com.movix.shared.presentation.model

data class DiscoverMovieState(
    val loading: Boolean = true,
    val error: Boolean = false,
    val items: List<MoviePresentationModel> = listOf()
) {

    companion object {
        val Initial = DiscoverMovieState()
    }

}