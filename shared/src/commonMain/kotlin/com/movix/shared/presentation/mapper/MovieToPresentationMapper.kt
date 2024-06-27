package com.movix.shared.presentation.mapper

import com.movix.shared.domain.model.MovieDomainModel
import com.movix.shared.presentation.model.MoviePresentationModel
import com.movix.shared.utils.DateUtils
import com.movix.shared.utils.TimeUtils

class MovieToPresentationMapper(
    private val dateUtils: DateUtils,
    private val timeUtils: TimeUtils
) : (MovieDomainModel) -> MoviePresentationModel {

    override fun invoke(p1: MovieDomainModel): MoviePresentationModel {
        return MoviePresentationModel(
            id = p1.id,
            title = p1.title,
            originalTitle = p1.originalTitle,
            overview = p1.overview,
            posterPath = p1.posterPath,
            backdropPath = p1.backdropPath,
            releaseDate = dateUtils.formatDate(p1.releaseDate, "dd MMMM yyyy"),
            genres = p1.genres,
            rating = p1.voteAverage.toFloat(),
            ratingStar = p1.voteAverage.toFloat().div(2),
            duration = timeUtils.formatHourMinutes(p1.runtime)
        )
    }
}