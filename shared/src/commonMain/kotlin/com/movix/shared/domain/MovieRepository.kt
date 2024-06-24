package com.movix.shared.domain

import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.model.MovieDomainModel
import com.movix.shared.domain.model.MovieListDomainModel

interface MovieRepository {

    suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel>

    suspend fun getPopularMovies(): ResultState<MovieListDomainModel>

    suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel>

    suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel>

}