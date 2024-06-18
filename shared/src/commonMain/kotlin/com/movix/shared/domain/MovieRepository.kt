package com.movix.shared.domain

import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.model.MovieListDomainModel

interface MovieRepository {

    suspend fun getMovies(
        preference: String,
        page: Int
    ): ResultState<MovieListDomainModel>

}