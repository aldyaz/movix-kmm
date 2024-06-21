package com.movix.shared.source.remote

import com.movix.shared.source.remote.model.MoviesDto

interface TmdbService {

    suspend fun getMovies(
        preference: String,
        page: Int
    ): MoviesDto

    suspend fun getNowPlaying(): MoviesDto

    suspend fun getPopular(): MoviesDto

    suspend fun getTopRated(): MoviesDto

}