package com.movix.shared.presentation.model

sealed class MovieDetailIntent {

    data class OnEnter(
        val movieId: Long
    ) : MovieDetailIntent()

}