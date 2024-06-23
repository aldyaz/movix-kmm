package com.movix.shared.presentation.model

data class HomeTabViewState(
    val nowPlaying: DiscoverMovieState = DiscoverMovieState.Initial,
    val popular: DiscoverMovieState = DiscoverMovieState.Initial,
    val topRated: DiscoverMovieState = DiscoverMovieState.Initial
)