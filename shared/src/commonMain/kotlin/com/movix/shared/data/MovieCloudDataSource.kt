package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto

interface MovieCloudDataSource {

    suspend fun getNowPlayingMovies(): HttpResult<MoviesDto>

    suspend fun getPopularMovies(): HttpResult<MoviesDto>

    suspend fun getTopRatedMovies(): HttpResult<MoviesDto>

    suspend fun getMovieDetail(id: Long): HttpResult<MovieDto>

}