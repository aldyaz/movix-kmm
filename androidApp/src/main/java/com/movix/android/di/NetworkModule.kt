package com.movix.android.di

import com.movix.shared.remote.HttpClientFactory
import com.movix.shared.source.remote.service.KtorTmdbMovieRemoteService
import com.movix.shared.source.remote.service.TmdbMovieRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient = HttpClientFactory().create()

    @Singleton
    @Provides
    fun provideTmdbService(
        httpClient: HttpClient
    ): TmdbMovieRemoteService = KtorTmdbMovieRemoteService(httpClient)

}