package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.mapper.HttpExceptionToDomainMapper
import com.movix.shared.domain.mapper.MovieListToDomainMapper
import com.movix.shared.domain.model.MovieListDomainModel

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val movieListToDomainMapper: MovieListToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : MovieRepository {

    override suspend fun getNowPlaying(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getNowPlaying()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getPopular(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getPopular()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getTopRated(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getTopRated()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }
}