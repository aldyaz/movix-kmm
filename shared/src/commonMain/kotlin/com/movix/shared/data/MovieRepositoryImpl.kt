package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.mapper.HttpExceptionToDomainMapper
import com.movix.shared.domain.mapper.MovieListToDomainMapper
import com.movix.shared.domain.mapper.MovieToDomainMapper
import com.movix.shared.domain.model.MovieDomainModel
import com.movix.shared.domain.model.MovieListDomainModel

class MovieRepositoryImpl(
    private val movieCloudDataSource: MovieCloudDataSource,
    private val movieListToDomainMapper: MovieListToDomainMapper,
    private val movieToDomainMapper: MovieToDomainMapper,
    private val httpExceptionToDomainMapper: HttpExceptionToDomainMapper
) : MovieRepository {

    override suspend fun getNowPlayingMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getNowPlayingMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getPopularMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getPopularMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getTopRatedMovies(): ResultState<MovieListDomainModel> {
        return when (val result = movieCloudDataSource.getTopRatedMovies()) {
            is HttpResult.Success -> ResultState.Success(
                movieListToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }

    override suspend fun getMovieDetail(id: Long): ResultState<MovieDomainModel> {
        return when (val result = movieCloudDataSource.getMovieDetail(id)) {
            is HttpResult.Success -> ResultState.Success(
                movieToDomainMapper(result.data)
            )

            is HttpResult.Error -> ResultState.Error(
                httpExceptionToDomainMapper(result.exception)
            )
        }
    }
}