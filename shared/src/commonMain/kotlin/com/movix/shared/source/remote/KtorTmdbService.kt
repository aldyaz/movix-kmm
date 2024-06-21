package com.movix.shared.source.remote

import com.movix.shared.remote.extension.apiCall
import com.movix.shared.source.remote.model.MoviesDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType

class KtorTmdbService(
    private val httpClient: HttpClient
) : TmdbService {

    override suspend fun getMovies(
        preference: String,
        page: Int
    ): MoviesDto {
        return apiCall {
            httpClient.get("movie/{preference}") {
                url {
                    parameters.append("preference", preference)
                    parameter("page", page)
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    override suspend fun getNowPlaying(): MoviesDto {
        return apiCall {
            httpClient.get("movie/now_playing") {
                contentType(ContentType.Application.Json)
            }
        }
    }

    override suspend fun getPopular(): MoviesDto {
        return apiCall {
            httpClient.get("movie/popular") {
                contentType(ContentType.Application.Json)
            }
        }
    }

    override suspend fun getTopRated(): MoviesDto {
        return apiCall {
            httpClient.get("movie/top_rated") {
                contentType(ContentType.Application.Json)
            }
        }
    }
}