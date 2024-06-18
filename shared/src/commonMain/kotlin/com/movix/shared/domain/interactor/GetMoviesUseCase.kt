package com.movix.shared.domain.interactor

import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.CoroutineContextProvider
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.base.UseCase
import com.movix.shared.domain.model.MovieListDomainModel
import com.movix.shared.domain.model.MoviePagingParam
import kotlinx.coroutines.withContext

class GetMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : UseCase<MoviePagingParam, MovieListDomainModel>() {

    override suspend fun execute(param: MoviePagingParam): ResultState<MovieListDomainModel> {
        return withContext(coroutineContextProvider.io) {
            movieRepository.getMovies(
                preference = param.preference,
                page = param.page
            )
        }
    }
}