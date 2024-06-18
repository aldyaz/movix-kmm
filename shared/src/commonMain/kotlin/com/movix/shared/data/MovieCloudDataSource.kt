package com.movix.shared.data

import com.movix.shared.data.base.HttpResult
import com.movix.shared.source.remote.model.MoviesDto

interface MovieCloudDataSource {

    suspend fun getMovies(
        preference: String,
        page: Int
    ): HttpResult<MoviesDto>

}