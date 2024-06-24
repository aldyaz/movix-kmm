package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.exception.HttpException
import com.movix.shared.source.remote.service.TmdbMovieRemoteService
import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto

class MovieCloudDataSourceImpl(
    private val tmdbMovieRemoteService: TmdbMovieRemoteService
) : MovieCloudDataSource {

    override suspend fun getNowPlayingMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbMovieRemoteService.getNowPlayingMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getPopularMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbMovieRemoteService.getPopularMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getTopRatedMovies(): HttpResult<MoviesDto> {
        return try {
            val result = tmdbMovieRemoteService.getTopRatedMovies()
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }

    override suspend fun getMovieDetail(id: Long): HttpResult<MovieDto> {
        return try {
            val result = tmdbMovieRemoteService.getMovieDetail(id)
            HttpResult.Success(result)
        } catch (err: HttpException) {
            HttpResult.Error(err)
        }
    }
}