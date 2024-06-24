package com.movix.android.di

import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.base.CoroutineContextProvider
import com.movix.shared.domain.interactor.GetMovieDetailUseCase
import com.movix.shared.domain.interactor.GetNowPlayingMoviesUseCase
import com.movix.shared.domain.interactor.GetPopularMoviesUseCase
import com.movix.shared.domain.interactor.GetTopRatedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideCoroutinesContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider.Default
    }

    @Provides
    fun provideGetNowPlayingUseCase(
        movieRepository: MovieRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetNowPlayingMoviesUseCase = GetNowPlayingMoviesUseCase(
        movieRepository = movieRepository,
        coroutineContextProvider = coroutineContextProvider
    )

    @Provides
    fun provideGetPopularUseCase(
        movieRepository: MovieRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetPopularMoviesUseCase = GetPopularMoviesUseCase(
        movieRepository = movieRepository,
        coroutineContextProvider = coroutineContextProvider
    )

    @Provides
    fun provideGetTopRatedUseCase(
        movieRepository: MovieRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(
        movieRepository = movieRepository,
        coroutineContextProvider = coroutineContextProvider
    )

    @Provides
    fun provideGetDetailUseCase(
        movieRepository: MovieRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): GetMovieDetailUseCase = GetMovieDetailUseCase(
        movieRepository = movieRepository,
        coroutineContextProvider = coroutineContextProvider
    )

}