package com.movix.android.di

import com.movix.shared.utils.DateUtils
import com.movix.shared.utils.TimeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {

    @Provides
    fun provideDateUtils(): DateUtils = DateUtils()

    @Provides
    fun provideTimeUtils(): TimeUtils = TimeUtils()

}