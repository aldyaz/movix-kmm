package com.movix.android.di

import com.movix.shared.data.MovieCloudDataSource
import com.movix.shared.data.MovieCloudDataSourceImpl
import com.movix.shared.data.MovieRepositoryImpl
import com.movix.shared.domain.MovieRepository
import com.movix.shared.domain.mapper.HttpExceptionToDomainMapper
import com.movix.shared.domain.mapper.MovieListToDomainMapper
import com.movix.shared.domain.mapper.MovieToDomainMapper
import com.movix.shared.source.remote.TmdbRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideHttpExceptionToDomainMapper(): HttpExceptionToDomainMapper =
        HttpExceptionToDomainMapper()

    @Provides
    fun provideMovieToDomainMapper(): MovieToDomainMapper = MovieToDomainMapper()

    @Provides
    fun provideMovieListToDomainMapper(
        movieToDomainMapper: MovieToDomainMapper
    ): MovieListToDomainMapper = MovieListToDomainMapper(movieToDomainMapper)

    @Provides
    fun provideMovieCloudDataSource(
        tmdbRemoteService: TmdbRemoteService
    ): MovieCloudDataSource = MovieCloudDataSourceImpl(tmdbRemoteService)

    @Provides
    fun provideMovieRepository(
        movieCloudDataSource: MovieCloudDataSource,
        movieListToDomainMapper: MovieListToDomainMapper,
        movieToDomainMapper: MovieToDomainMapper,
        httpExceptionToDomainMapper: HttpExceptionToDomainMapper
    ): MovieRepository = MovieRepositoryImpl(
        movieCloudDataSource = movieCloudDataSource,
        movieListToDomainMapper = movieListToDomainMapper,
        movieToDomainMapper = movieToDomainMapper,
        httpExceptionToDomainMapper = httpExceptionToDomainMapper
    )

}