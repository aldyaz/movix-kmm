package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.source.remote.model.MoviesDto

interface MovieCloudDataSource {

    suspend fun getMovies(
        preference: String,
        page: Int
    ): HttpResult<MoviesDto>

    suspend fun getNowPlaying(): HttpResult<MoviesDto>

    suspend fun getPopular(): HttpResult<MoviesDto>

    suspend fun getTopRated(): HttpResult<MoviesDto>

}