package com.movix.shared.domain.interactor

import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.CoroutineContextProvider
import com.movix.shared.domain.base.ResultState
import com.movix.shared.domain.base.UseCase
import com.movix.shared.domain.model.MovieDomainModel
import kotlinx.coroutines.withContext

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : UseCase<Long, MovieDomainModel>() {

    override suspend fun execute(param: Long): ResultState<MovieDomainModel> {
        return withContext(coroutineContextProvider.io) {
            movieRepository.getMovieDetail(param)
        }
    }
}