package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.mapper.HttpExceptionToDomainMapper
import com.movix.shared.domain.mapper.MovieListToDomainMapper
import com.movix.shared.domain.model.MovieListDomainModel

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val moviesToDomainMapper: MovieListToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : MovieRepository {

    override suspend fun getMovies(
        preference: String,
        page: Int
    ): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getMovies(preference, page)) {
            is HttpResult.Success -> ResultState.Success(
                moviesToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }
}