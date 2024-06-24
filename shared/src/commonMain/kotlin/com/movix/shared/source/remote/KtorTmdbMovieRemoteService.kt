package com.movix.shared.source.remote

import com.movix.shared.remote.extension.apiCall
import com.movix.shared.source.remote.model.MovieDto
import com.movix.shared.source.remote.model.MoviesDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.path

class KtorTmdbMovieRemoteService(
    private val httpClient: HttpClient
) : TmdbMovieRemoteService {

    override suspend fun getNowPlayingMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/now_playing")
        }
    }

    override suspend fun getPopularMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/popular")
        }
    }

    override suspend fun getTopRatedMovies(): MoviesDto {
        return apiCall {
            httpClient.get("movie/top_rated")
        }
    }

    override suspend fun getMovieDetail(id: Long): MovieDto {
        return apiCall {
            httpClient.get("movie/{movie_id}") {
                url {
                    path(id.toString())
                }
            }
        }
    }
}