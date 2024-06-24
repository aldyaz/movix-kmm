package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto

interface MovieCloudDataSource {

    suspend fun getNowPlaying(): HttpResult<MoviesDto>

    suspend fun getPopular(): HttpResult<MoviesDto>

    suspend fun getTopRated(): HttpResult<MoviesDto>

    suspend fun getMovieDetail(id: Long): HttpResult<MovieDto>

}