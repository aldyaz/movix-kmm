package com.movix.shared.presentation.mapper

import com.movix.shared.domain.model.MovieDomainModel
import com.movix.shared.presentation.model.MoviePresentationModel

class MovieToPresentationMapper : (MovieDomainModel) -> MoviePresentationModel {

    override fun invoke(p1: MovieDomainModel): MoviePresentationModel {
        return MoviePresentationModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.originalTitle,
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = p1.releaseDate,
            genres = p1.genres
        )
    }
}