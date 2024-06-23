package com.movix.shared.source.remote

import com.movix.shared.source.remote.model.MoviesDto

interface TmdbRemoteService {

    suspend fun getNowPlaying(): MoviesDto

    suspend fun getPopular(): MoviesDto

    suspend fun getTopRated(): MoviesDto

}