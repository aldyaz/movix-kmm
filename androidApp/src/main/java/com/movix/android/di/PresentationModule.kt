package com.movix.android.di

import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class PresentationModule {

    @Provides
    fun provideMovieToPresentationMapper(): MovieToPresentationMapper = MovieToPresentationMapper()

}