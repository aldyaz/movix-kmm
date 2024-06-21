package com.movix.shared.domain.interactor

import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.CoroutineContextProvider
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.base.UseCase
import com.movix.shared.domain.model.MovieListDomainModel
import kotlinx.coroutines.withContext

class GetPopularUseCase(
    private val movieRepository: MovieRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : UseCase<Unit, MovieListDomainModel>() {

    override suspend fun execute(param: Unit): ResultState<MovieListDomainModel> {
        return withContext(coroutineContextProvider.io) {
            movieRepository.getPopular()
        }
    }
}