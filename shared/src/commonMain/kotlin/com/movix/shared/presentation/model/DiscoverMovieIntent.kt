package com.movix.shared.presentation.model

sealed class DiscoverMovieIntent {

    data object OnRefresh : DiscoverMovieIntent()

}