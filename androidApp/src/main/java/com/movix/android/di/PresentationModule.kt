package com.movix.android.di

import com.movix.shared.presentation.mapper.MovieToPresentationMapper
import com.movix.shared.utils.DateUtils
import com.movix.shared.utils.TimeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class PresentationModule {

    @Provides
    fun provideMovieToPresentationMapper(
        dateUtils: DateUtils,
        timeUtils: TimeUtils
    ): MovieToPresentationMapper = MovieToPresentationMapper(
        dateUtils = dateUtils,
        timeUtils = timeUtils
    )

}