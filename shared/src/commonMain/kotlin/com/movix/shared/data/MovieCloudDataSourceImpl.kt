package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.exception.HttpException
import com.movix.shared.source.remote.TmdbRemoteService
import com.movix.shared.source.remote.model.MoviesDto

class MovieCloudDataSourceImpl(
    private val tmdbRemoteService: TmdbRemoteService
) : MovieCloudDataSource {

    override suspend fun getNowPlaying(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getNowPlaying()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getPopular(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getPopular()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getTopRated(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbRemoteService.getTopRated()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }
}