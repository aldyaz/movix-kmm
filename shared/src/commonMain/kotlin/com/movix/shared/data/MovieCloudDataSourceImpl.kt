package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.exception.HttpException
import com.movix.shared.source.remote.TmdbService
import com.movix.shared.source.remote.model.MoviesDto

class MovieCloudDataSourceImpl(
    private val tmdbService: TmdbService
) : MovieCloudDataSource {

    override suspend fun getMovies(preference: String, page: Int): HttpResult<MoviesDto> {
        return try {
            val result = tmdbService.getMovies(preference, page)
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }
}