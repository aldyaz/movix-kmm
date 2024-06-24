package com.movix.shared.source.remote

import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto

interface TmdbRemoteService {

    suspend fun getNowPlaying(): MoviesDto

    suspend fun getPopular(): MoviesDto

    suspend fun getTopRated(): MoviesDto

    suspend fun getMovieDetail(id: Long): MovieDto

}