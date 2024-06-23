package com.movix.shared.presentation.model

data class DiscoverTypePresentationModel(
    val type: Type,
    val items: List<MoviePresentationModel>
) {

    enum class Type(
        val title: String
    ) {
        NOW_PLAYING("Now Playing"),
        POPULAR("Popular"),
        TOP_RATED("Top Rated")
    }

}