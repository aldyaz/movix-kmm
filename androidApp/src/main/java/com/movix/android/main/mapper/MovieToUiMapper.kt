package com.movix.android.main.mapper

import com.movix.android.model.MovieUiModel
import com.movix.shared.presentation.model.MoviePresentationModel

class MovieToUiMapper : (MoviePresentationModel) -> MovieUiModel {

    override fun invoke(p1: MoviePresentationModel): MovieUiModel {
        return MovieUiModel(
            id = p1.id,
            title = p1.title,
            posterPath = p1.posterPath
        )
    }
}