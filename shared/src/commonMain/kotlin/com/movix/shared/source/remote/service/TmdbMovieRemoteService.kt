package com.movix.shared.source.remote.service

import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto

interface TmdbMovieRemoteService {

    suspend fun getNowPlayingMovies(): MoviesDto

    suspend fun getPopularMovies(): MoviesDto

    suspend fun getTopRatedMovies(): MoviesDto

    suspend fun getMovieDetail(id: Long): MovieDto

}