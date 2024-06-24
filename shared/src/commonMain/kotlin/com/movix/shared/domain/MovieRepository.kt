package com.movix.shared.domain

import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.model.MovieDomainModel
import com.movix.shared.domain.model.MovieListDomainModel

interface MovieRepository {

    suspend fun getNowPlaying(): ResultState<MovieListDomainModel>

    suspend fun getPopular(): ResultState<MovieListDomainModel>

    suspend fun getTopRated(): ResultState<MovieListDomainModel>

    suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel>

}