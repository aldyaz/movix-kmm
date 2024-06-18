package com.movix.shared.domain.mapper

import com.movix.shared.domain.model.MovieListDomainModel
import com.movix.shared.source.remote.model.MoviesDto

class MovieListToDomainMapper(
    private val movieToDomainMapper: MovieToDomainMapper
) : (MoviesDto) -> MovieListDomainModel {

    override fun invoke(p1: MoviesDto): MovieListDomainModel {
        return MovieListDomainModel(
            page = p1.page ?: 0,
            movies = p1.results?.map(movieToDomainMapper).orEmpty()
        )
    }
}